package net.jericko.accessories.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Vector;

public class SpeedItem extends Item implements ICurioItem {
    //TODO: Add particles
    public SpeedItem(Properties p_41383_) {
        super(p_41383_);
    }

    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        boolean hasEffect = player.hasEffect(MobEffects.MOVEMENT_SPEED);

        if(!hasEffect) {
            player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 20, false, false, false)));

        }
    }

    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();

        player.removeEffect(MobEffects.MOVEMENT_SPEED);
    }
}
