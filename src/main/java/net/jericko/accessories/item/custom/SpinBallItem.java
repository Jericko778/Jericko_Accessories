package net.jericko.accessories.item.custom;

import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.entity.custom.SpinBallEntity;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.client.CursedBallRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;

public class SpinBallItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    int animationCur;

    public SpinBallItem(Properties p_41383_) {
        super(p_41383_);
    }

    public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
        return !p_43412_.isCreative();
    }

    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.SPEAR;
    }

    public int getUseDuration(ItemStack p_43419_) {
        return 72000;
    }

    private PlayState predicate(AnimationState animationState) {
        if (animationCur == 1){
            animationState.getController().setAnimation(RawAnimation.begin().then("animation.model.spin", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int p_43397_) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(itemStack) - p_43397_;
            if (i >= 10) {
                    if (!level.isClientSide) {
                        itemStack.hurtAndBreak(1, player, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(livingEntity.getUsedItemHand());
                        });
                            SpinBallEntity spinBall = new SpinBallEntity(level, livingEntity, itemStack);
                            spinBall.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F + (float) 0.0F, 1.0F);
                            if (player.getAbilities().instabuild) {
                                spinBall.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            level.addFreshEntity(spinBall);
                            level.playSound((Player) null, spinBall, SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                            if (!player.getAbilities().instabuild) {
                                player.getInventory().removeItem(itemStack);
                    }
                }
            }
        }
        animationCur = 0;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        animationCur = 1;
        player.startUsingItem(interactionHand);
        return InteractionResultHolder.consume(itemstack);
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
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private CursedBallRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    renderer = new CursedBallRenderer();
                }

                return this.renderer;
            }
        });
    }
}