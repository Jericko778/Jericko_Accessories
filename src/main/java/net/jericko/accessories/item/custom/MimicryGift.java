package net.jericko.accessories.item.custom;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class MimicryGift extends BuffAccessory implements ICurioItem {

    public MimicryGift(Properties properties, Attribute[] attribute, int[] born, double[] amount) {
        super(properties, attribute, born, amount);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if(slotContext.entity().tickCount % 100 == 0){
            slotContext.entity().heal(1);
        }
    }
}
