package net.jericko.accessories.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class DashItem extends Item implements ICurioItem {
    public DashItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
        Vec3 playerLook = player.getViewVector(1);
        Vec3 dashVec = new Vec3(playerLook.x(), 0, playerLook.z());
        player.addDeltaMovement(dashVec);
        player.getCooldowns().addCooldown(this, 50);

        return InteractionResultHolder.pass(player.getItemInHand(p_41434_));
    }

}
