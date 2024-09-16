package net.jericko.accessories.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.entity.custom.ReticleEntity;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
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
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.stringtemplate.v4.misc.Misc;

import javax.swing.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PistolItem extends Item {

    private static boolean reticleExists;
    private static ReticleEntity reticle;
    //private static final int range = 50;
    private int c = 0;

    public PistolItem(Properties p_41383_) {super(p_41383_);}


    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(itemStack, level, entity, i, b);

        Player player = Minecraft.getInstance().player;

        if(player != null){

            // Creates a Reticle
            // Changes to focused reticle if focusing
            if (!level.isClientSide && !reticleExists && player.isHolding(this) /* && Chaos Glasses equipped */) {
                ItemStack itemstack = ModItems.CHAOSFOCUSRETICLE.get().getDefaultInstance();
                reticle = new ReticleEntity(level, player);
                reticle.setItem(itemstack);
                reticle.setNoGravity(true);
                level.addFreshEntity(reticle);
                reticleExists = true;
            }

            //Removes reticle if not holding pistol

            Vec3 pos = player.position();
            Vec3 direction = player.getViewVector(1);
            if(reticleExists && !player.isHolding(this)){
                reticleExists = false;
                reticle.kill();
            }
          }


    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        // Shoots gun at targeted entity

        level.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,1.0F);


        Vec3 pos = player.getEyePosition();
        Vec3 direction = player.getViewVector(1);

        for(int range = 0; range <50; range++){
            List<Mob> hi = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x*range, pos.y + direction.y*range, pos.z + direction.z*range));

            if(level.getBlockState(new BlockPos((int)(pos.x + direction.x*range), (int)(pos.y + direction.y*range), (int)(pos.z + direction.z*range))).getBlock() != Blocks.AIR) {
                break;
            }
            for(Entity e: hi) {
                if(e.getType() == ModEntities.CHAOSFOCUSRETICLE.get()){
                    continue;
                }
                boolean blocked = false;
                for(int i = 0; i<range; i++){
                    if(level.getBlockState(new BlockPos(pos.add(direction.multiply(i,i,i)))))
                }

                e.hurt(new DamageSource(new Holder<DamageType>() {
                    @Override
                    public DamageType value() {
                        return null;
                    }

                    @Override
                    public boolean isBound() {
                        return false;
                    }

                    @Override
                    public boolean is(ResourceLocation p_205713_) {
                        return false;
                    }

                    @Override
                    public boolean is(ResourceKey<DamageType> p_205712_) {
                        return false;
                    }

                    @Override
                    public boolean is(Predicate<ResourceKey<DamageType>> p_205711_) {
                        return false;
                    }

                    @Override
                    public boolean is(TagKey<DamageType> p_205705_) {
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
                    public boolean canSerializeIn(HolderOwner<DamageType> p_255833_) {
                        return false;
                    }
                }), 45);
            }
        }


        return super.use(level, player, hand);
    }

}
