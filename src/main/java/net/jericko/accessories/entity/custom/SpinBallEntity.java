package net.jericko.accessories.entity.custom;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.*;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Objects;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class SpinBallEntity extends AbstractArrow implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private ItemStack ballItem = new ItemStack(ModItems.CURSEDBALL.get());
    private int bounces;
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    int animationCur;

    public SpinBallEntity(Level level) {
        super(ModEntities.CURSEDBALL.get(), level);
        bounces = 10;
    }

    public SpinBallEntity(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(ModEntities.CURSEDBALL.get(), livingEntity, level);
        this.ballItem = itemStack.copy();;
        bounces = 10;
    }

    public SpinBallEntity(EntityType<SpinBallEntity> entityType, Level level) {
        super(entityType, level);
        bounces = 10;
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("animation.model.spin", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingentity) {
            f += EnchantmentHelper.getDamageBonus(this.ballItem, livingentity.getMobType());
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, (Entity)(entity1 == null ? this : entity1));
        //this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.ENDER_DRAGON_HURT;
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
                }

                this.doPostHurtEffects(livingentity1);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;

        this.playSound(soundevent, f1, 1.0F);
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();;
        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.1D;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected void onHit(HitResult p_37260_) {
        HitResult.Type hitresult$type = p_37260_.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)p_37260_);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, p_37260_.getLocation(), GameEvent.Context.of(this, (BlockState)null));
        } else if (hitresult$type == HitResult.Type.BLOCK) {
                BlockHitResult blockhitresult = (BlockHitResult)p_37260_;
                if(((BlockHitResult) p_37260_).getBlockPos().above().getY() < this.getY()) {
                    this.onHitBlock(blockhitresult);
                    BlockPos blockpos = blockhitresult.getBlockPos();
                    this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
                }else{
                    this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
                }
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    protected ItemStack getPickupItem() {
        return ballItem;
    }

    @SubscribeEvent
    public void pullBack(InputEvent.MouseButton event){
        if(event.getButton() == GLFW.GLFW_MOUSE_BUTTON_2){
            Vec3 diff = this.position().subtract(Objects.requireNonNull(this.getOwner()).position().add(0,0,0));
            diff.normalize().scale(2);
           this.setDeltaMovement(this.getDeltaMovement().subtract(diff));

            this.getOwner().kill();
        }
    }
}
