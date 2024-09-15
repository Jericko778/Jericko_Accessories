package net.jericko.accessories.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Either;
import net.jericko.accessories.entity.custom.ReticleEntity;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PistolItem extends Item {
    public PistolItem(Properties p_41383_) {super(p_41383_);}


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);

        //

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {

        // Creates a Reticle

        ItemStack itemstack = ModItems.CHAOSRETICLE.get().getDefaultInstance();
        if (!level.isClientSide) {
            ReticleEntity reticle = new ReticleEntity(level, player);
            reticle.setItem(itemstack);
            reticle.setNoGravity(true);
            level.addFreshEntity(reticle);
        }


        // Shoots gun at targeted entity

        level.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,1.0F);

        Vec3 pos = player.position();

        for(int range = 1; range<100; range++){
            Vec3 direction = player.getViewVector(1);
            List<Mob> hi = level.getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x*range, pos.y + direction.y*range, pos.z + direction.z*range));
            
            for(Entity i: hi) {
                if(i != Entity.NULL){
                    EyeOfEnder e = new EyeOfEnder(level, 1, 1, 1);
                    e.setPos(player.position());
                }
                i.hurt(new DamageSource(new Holder<DamageType>() {
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

        return super.use(level, player, p_41434_);
    }
}
