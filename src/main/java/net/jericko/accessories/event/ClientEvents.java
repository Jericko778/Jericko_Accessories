package net.jericko.accessories.event;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
            if(KeyBinding.FIRST_SLOT.consumeClick()){
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("HI"));
            }
        }

    }

    @Mod.EventBusSubscriber(modid = Accessories.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void OnKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.FIRST_SLOT);
        }
    }
}
