package net.jericko.accessories.item.custom;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.UUID;

public class BuffAccessory extends Item implements ICurioItem {
    private final Attribute[] type;
    private final int[] born;
    private final double[] amount;
    private final UUID[] ids;

    public BuffAccessory(Properties properties, Attribute[] attribute, int[] born, double[] amount) {
        super(properties);
        this.type = attribute;
        this.born = born;
        this.amount = amount;
        this.ids = new UUID[born.length];
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        for(int i = 0; i < type.length; i++) {
            AttributeModifier cur = new AttributeModifier( "" + i, amount[i] * born[i], AttributeModifier.Operation.ADDITION);
            Objects.requireNonNull(slotContext.entity().getAttribute(type[i])).addTransientModifier(cur);
            ids[i] = cur.getId();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        for(int i = 0; i < type.length; i++) {
            Objects.requireNonNull(slotContext.entity().getAttribute(type[i])).removeModifier(ids[i]);
            if (type[i] == Attributes.MAX_HEALTH) {
                if (slotContext.entity().getHealth() > Objects.requireNonNull(slotContext.entity().getAttribute(type[i])).getBaseValue()) {
                    slotContext.entity().setHealth(slotContext.entity().getMaxHealth());
                }
            }
        }
    }
}
