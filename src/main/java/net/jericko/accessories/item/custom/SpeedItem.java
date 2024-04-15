package net.jericko.accessories.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SpeedItem extends Item implements ICurioItem {
    //TODO: Add particles
    public SpeedItem(Properties p_41383_) {
        super(p_41383_);
    }

    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        boolean hasEffect = player.hasEffect(MobEffects.MOVEMENT_SPEED);

        if(!hasEffect) {
            player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, false)));
        }
    }

    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.removeEffect(MobEffects.MOVEMENT_SPEED);
    }
}
