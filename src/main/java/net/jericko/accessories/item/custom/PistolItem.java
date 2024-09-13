package net.jericko.accessories.item.custom;

import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class PistolItem extends Item {
    public PistolItem(Properties p_41383_) {super(p_41383_);}


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);

        //

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        level.playSound(null, player.blockPosition(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,1.0F);

        Vec3 pos = player.position();
        Vec3 direction = player.getViewVector(1);
        level.addParticle(ModParticles.CITRINE_PARTICLES.get(), player.blockPosition().getX(), player.blockPosition().getY(), player.blockPosition().getZ(),1,1,1);

        return super.use(level, player, p_41434_);
    }
}
