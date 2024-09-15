package net.jericko.accessories.event;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.custom.DashItem;
import net.jericko.accessories.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Accessories.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            Player player = Minecraft.getInstance().player;

            if(KeyBinding.DASH.consumeClick() && DashItem.isEquipped && !player.getCooldowns().isOnCooldown(ModItems.DASH.get())){
                Vec3 playerLook = player.getViewVector(1);
                Vec3 dashVec = new Vec3(playerLook.x(), playerLook.y(), playerLook.z());
                player.setDeltaMovement(dashVec);
                player.getCooldowns().addCooldown(ModItems.DASH.get(), 30);
            }
        }

    }
    @Mod.EventBusSubscriber(modid = Accessories.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void OnKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.DASH);
        }

    }


}
