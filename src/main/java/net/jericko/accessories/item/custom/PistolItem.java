package net.jericko.accessories.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.entity.custom.ReticleEntity;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.client.CrescentMoonRenderer;
import net.jericko.accessories.item.client.KnuckleblasterRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.stringtemplate.v4.misc.Misc;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class PistolItem extends Item implements GeoItem {

    private static boolean reticleExists, focus, firing;
    private static ReticleEntity reticle;
    private Entity focusedEntity;
    //private static final int range = 50;
    private int c = 0;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


    public PistolItem(Properties p_41383_) {super(p_41383_);}



    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(itemStack, level, entity, i, b);

        Player player = Minecraft.getInstance().player;

        if(player != null){

            // Creates a Reticle
            // Changes to focused reticle if focusing
            if (!level.isClientSide && !reticleExists && player.isHolding(this) && focus/* && Chaos Glasses equipped */) {
                ItemStack itemstack = ModItems.CHAOSFOCUSRETICLE.get().getDefaultInstance();
                reticle = new ReticleEntity(level, player);
                reticle.setItem(itemstack);
                reticle.setNoGravity(true);
                level.addFreshEntity(reticle);
                reticleExists = true;
            }

            Vec3 pos = player.getEyePosition();
            Vec3 direction = player.getViewVector(1);
            if(reticleExists && focus){
                int aimbotRange = 30;
                List<Mob> hi = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x - aimbotRange, pos.y - aimbotRange, pos.z - aimbotRange, pos.x + aimbotRange, pos.y + aimbotRange, pos.z + aimbotRange));
                if(hi.isEmpty()){
                    reticle.kill();
                    focus = false;
                    reticleExists = false;
                }
                Vec3 closestEntity = new Vec3(1000,1000,1000);
                for (Entity e : hi) {
                    if (e.getType() == ModEntities.CHAOSFOCUSRETICLE.get() || e.equals(player)) {
                        continue;
                    }
                    if(e != Entity.NULL){
                        if((e.position().subtract(pos)).length() < (closestEntity.subtract(pos)).length()){
                            closestEntity = e.position();
                            focusedEntity = e;
                        }
                    }
                }
                if(focusedEntity != Entity.NULL && focusedEntity != null){
                    Vec3 retPos = pos.add((focusedEntity.getEyePosition().subtract(pos).normalize()).multiply(2,2,2));
                    reticle.setPos(retPos.subtract(new Vec3(0,0.3,0)));
                }

            }


            //Removes reticle if not holding pistol

            if(reticleExists && (!player.isHolding(this) || !focus)){
                reticleExists = false;
                reticle.kill();
            }
          }


    }

    @SubscribeEvent
    public static void StartFocus(InputEvent.Key event){
        Player player = Minecraft.getInstance().player;
        if(player != null && event.getKey() == 340){
            if(event.getAction() == GLFW.GLFW_PRESS){
                focus = true;

            }
            if(event.getAction() == GLFW.GLFW_RELEASE){
                focus = false;
            }
        }
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        // Shoots gun at targeted entity

        level.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,1.0F);
        firing = true;

        Vec3 pos = player.getEyePosition();
        Vec3 direction = player.getViewVector(1);

        if(focus){
            focusedEntity.hurt(player.damageSources().generic(), 60);
        }

        if(!focus) {
            for (int range = 1; range < 40; range++) {
                List<Mob> hi = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x * range, pos.y + direction.y * range, pos.z + direction.z * range));

                for (Entity e : hi) {
                    if (e.getType() == ModEntities.CHAOSFOCUSRETICLE.get()) {
                        continue;
                    }

                    boolean blocked = false;

                    for (int i = 0; i < range; i++) {
                        Entity b = new ReticleEntity(level, player);
                        b.setPos(pos.add(direction.multiply(i, i, i)));
                        level.addFreshEntity(b);
                        if (b.isColliding(b.blockPosition(), b.getBlockStateOn())) {
                            blocked = true;
                        }
                        b.kill();
                    }

                    if (!blocked) {
                        e.hurt(player.damageSources().generic(), 45);
                    }
                }
            }
        }


        return super.use(level, player, hand);
    }

    public static boolean getFocus(){
        return focus;
    }


    private PlayState predicate(AnimationState animationState) {
        if(firing){
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.model.fire", Animation.LoopType.PLAY_ONCE));
            animationState.setControllerSpeed(2);
            animationState.resetCurrentAnimation();
            firing = false;
        }
        return PlayState.CONTINUE;
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
            private CrescentMoonRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    renderer = new CrescentMoonRenderer();
                }

                return this.renderer;
            }
        });
    }
}
