package net.jericko.accessories.item.custom;

import com.sun.jna.platform.win32.WinBase;
import net.jericko.accessories.Accessories;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class SpeedItem extends Item implements ICurioItem {
    //TODO: Add particles
    public SpeedItem(Properties p_41383_) {
        super(p_41383_);
    }
    private static Level lightning = null;
    private static boolean hasEffect, override;
    private int counter;

    @Override

    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        lightning = slotContext.entity().level();
        hasEffect = player.hasEffect(MobEffects.MOVEMENT_SPEED);

        if(override){
            if(counter == 0){
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, lightning);
                bolt.setVisualOnly(true);
                bolt.setPos(player.position());
                lightning.addFreshEntity(bolt);
                player.removeEffect(MobEffects.MOVEMENT_SPEED);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 20, false, false, false));
                counter++;
            }
            if(!hasEffect){
                override = false;
                counter = 0;
            }
        }
        if(!hasEffect && !override) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 4, false, false, false));
        }
    }


    @SubscribeEvent
    public static void LightningBoost(InputEvent.Key event){
        Player player = Minecraft.getInstance().player;
        if(player != null && hasEffect && event.getKey() == 341 && event.getAction() == GLFW.GLFW_PRESS){
            override = true;
        }
    }




    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        player.removeEffect(MobEffects.MOVEMENT_SPEED);
        override = false;
    }
}
