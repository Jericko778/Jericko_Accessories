package net.jericko.accessories.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class BuffAccessory extends Item implements ICurioItem {
    private Attribute type;
    private int born;

    public BuffAccessory(Properties properties, Attribute attribute, int born) {
        super(properties);
        this.type = attribute;
        this.born = born;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = Minecraft.getInstance().player;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        Objects.requireNonNull(slotContext.entity().getAttribute(type)).setBaseValue(slotContext.entity().getMaxHealth() + (10 * born));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        Objects.requireNonNull(slotContext.entity().getAttribute(type)).setBaseValue(slotContext.entity().getMaxHealth() - (10 * born));
        if(slotContext.entity().getHealth() > slotContext.entity().getMaxHealth()){
            slotContext.entity().setHealth(slotContext.entity().getMaxHealth());
        }
    }
}
