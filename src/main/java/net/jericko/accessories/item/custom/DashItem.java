package net.jericko.accessories.item.custom;

import net.jericko.accessories.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class DashItem extends Item implements ICurioItem {
    //TODO: Add particles and cooldown indicator
    public static boolean isEquipped = false;

    public DashItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
        Vec3 playerLook = player.getViewVector(1);
        Vec3 dashVec = new Vec3(playerLook.x(), 0, playerLook.z());
        player.addDeltaMovement(dashVec);
        player.getCooldowns().addCooldown(ModItems.DASH.get(), 50);

        return InteractionResultHolder.pass(player.getItemInHand(p_41434_));
    }

    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        isEquipped = true;
    }

    public void onUnequip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        isEquipped = false;
    }

    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return !isEquipped;
    }
}
