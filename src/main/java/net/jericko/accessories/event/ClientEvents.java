package net.jericko.accessories.event;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.client.BulletOverlay;
import net.jericko.accessories.client.ReticleOverlay;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.custom.DashItem;
import net.jericko.accessories.item.custom.PistolItem;
import net.jericko.accessories.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Accessories.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{

        public static ModelPart head;
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

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent tick){

        }


        @SubscribeEvent
        public static void renderCrosshair(RenderGuiOverlayEvent event){
            if(VanillaGuiOverlay.CROSSHAIR.type() == event.getOverlay()){
                if(Minecraft.getInstance().player.isHolding(ModItems.CHAOSPISTOL.get())){
                    event.setCanceled(true);
                }
            }
        }


        @SubscribeEvent
        public static void focusFOV(ViewportEvent.ComputeFov event){
            if(Minecraft.getInstance().player.isHolding(ModItems.CHAOSPISTOL.get()) && PistolItem.getFocus()){
                event.setFOV(event.getFOV()*0.9);//Mth.lerp(0.02, (float)event.getFOV(), (float)(event.getFOV()*0.8)));
            }
        }

        @SubscribeEvent
        public static void shades(RenderPlayerEvent event){
            head = event.getRenderer().getModel().getHead();
        }

        public static ModelPart getHead(){
            return head;
        }




    }
    @Mod.EventBusSubscriber(modid = Accessories.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void OnKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.DASH);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("reticle", ReticleOverlay.HUD_RETICLE);
            event.registerAboveAll("bullets", BulletOverlay.BULLET_OVERLAY);
        }

    }
}
