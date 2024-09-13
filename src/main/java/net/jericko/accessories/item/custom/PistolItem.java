package net.jericko.accessories.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PistolItem extends Item {
    public PistolItem(Properties p_41383_) {super(p_41383_);}


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        level.playSound(null, player.blockPosition(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1,1);

        return super.use(level, player, p_41434_);
    }
}
