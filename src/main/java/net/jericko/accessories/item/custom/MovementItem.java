package net.jericko.accessories.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MovementItem extends Item implements ICurioItem {
    public MovementItem(Properties properties) {super(properties);}

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = Minecraft.getInstance().player;

    }
}
