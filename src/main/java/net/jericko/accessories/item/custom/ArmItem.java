package net.jericko.accessories.item.custom;

import com.mojang.datafixers.util.Either;
import net.jericko.accessories.entity.custom.KnuckleBlasterExplosion;
import net.jericko.accessories.item.client.KnuckleblasterRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ArmItem extends Item implements GeoItem {
    //So far only added animation of the item
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public ArmItem(Properties p_41383_) {
        super(p_41383_);
    }

    private PlayState predicate(AnimationState animationState) {
        if(Minecraft.getInstance().player.isShiftKeyDown() && !animationState.getController().getAnimationState().equals(AnimationController.State.RUNNING)) {
            animationState.getController().setAnimation(RawAnimation.begin().then("punch", Animation.LoopType.PLAY_ONCE));
            animationState.resetCurrentAnimation();
            punchEffect();
            //wtf
        }
        return PlayState.CONTINUE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        Vec3 pos = player.position();
        Vec3 direction = player.getViewVector(5);

        List<Mob> hi = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x * 2F, pos.y + direction.y, pos.z + direction.z * 2F));
        for (LivingEntity j : hi) {
            j.hurt( new DamageSource(new Holder<DamageType>() {
                @Override
                public DamageType value() {
                    return null;
                }

                @Override
                public boolean isBound() {
                    return false;
                }

                @Override
                public boolean is(ResourceLocation resourceLocation) {
                    return false;
                }

                @Override
                public boolean is(ResourceKey<DamageType> resourceKey) {
                    return false;
                }

                @Override
                public boolean is(Predicate<ResourceKey<DamageType>> predicate) {
                    return false;
                }

                @Override
                public boolean is(TagKey<DamageType> tagKey) {
                    return false;
                }

                @Override
                public Stream<TagKey<DamageType>> tags() {
                    return Stream.empty();
                }

                @Override
                public Either<ResourceKey<DamageType>, DamageType> unwrap() {
                    return null;
                }

                @Override
                public Optional<ResourceKey<DamageType>> unwrapKey() {
                    return Optional.empty();
                }

                @Override
                public Kind kind() {
                    return null;
                }

                @Override
                public boolean canSerializeIn(HolderOwner<DamageType> holderOwner) {
                    return false;
                }
            }), 200);
        }

        for(Entity i :level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x + direction.x - 20F, pos.y + direction.y - 20F, pos.z + direction.z - 20F, pos.x + direction.x + 20F, pos.y + direction.y + 20F, pos.z + direction.z + 20F))){
            if(player.canAttack((LivingEntity) i)){
                KnuckleBlasterExplosion explosion = new KnuckleBlasterExplosion(level, player, pos.x + direction.x, pos.y + direction.y + 2, pos.z + direction.z, 10F, new ArrayList<BlockPos>());
                explosion.explode();
                explosion.finalizeExplosion(true);
                List<Mob> hello = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x + direction.x - 20F, pos.y + direction.y - 20F, pos.z + direction.z - 20F, pos.x + direction.x + 20F, pos.y + direction.y + 20F, pos.z + direction.z + 20F));
                for (LivingEntity j : hello) {
                    j.knockback(500F, 500F, 500F);
                }
            }
        }
        if(player.canAttack(player)) {
            KnuckleBlasterExplosion explosion = new KnuckleBlasterExplosion(level, player, pos.x + direction.x, pos.y + direction.y + 2, pos.z + direction.z, 10F, new ArrayList<BlockPos>());
            explosion.explode();
            explosion.finalizeExplosion(true);
            List<Mob> hello = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x + direction.x - 20F, pos.y + direction.y - 20F, pos.z + direction.z - 20F, pos.x + direction.x + 20F, pos.y + direction.y + 20F, pos.z + direction.z + 20F));
            for (LivingEntity i : hello) {
                i.knockback(500F, 500F, 500F);
            }
        }
        return super.use(level, player, p_41434_);
    }

    public void punchEffect(){
        Player player = Minecraft.getInstance().player;
        ServerLevel level = null;
        Level hi = player.level();
        if(hi instanceof ServerLevel) {
            level = (ServerLevel) hi;


            Vec3 pos = player.position();
            Vec3 direction = player.getViewVector(5);

            List<BlockPos> n = new ArrayList<>();
            //Explosion explosion = new Explosion(player, pos.x + direction.x, pos.y + direction.y + 2, pos.z + direction.z, 10F, n);

            List<Mob> hello = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x + direction.x - 20F, pos.y + direction.y - 20F, pos.z + direction.z - 20F, pos.x + direction.x + 20F, pos.y + direction.y + 20F, pos.z + direction.z + 20F));
            for (LivingEntity i : hello) {
                i.knockback(500F, 500F, 500F);
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
            controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
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
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private KnuckleblasterRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    renderer = new KnuckleblasterRenderer();
                }

                return this.renderer;
            }
        });
    }

    @SubscribeEvent
    public static void renderPlayerPre(RenderPlayerEvent.Pre event) {
        //if (event.getEntity().getInventory().offhand.get(1).is(ModItems.KNUCKLEBLASTER.get())) {
            event.getRenderer().getModel().leftSleeve.visible = false;
            event.getRenderer().getModel().rightSleeve.visible = false;

       // }
    }
}