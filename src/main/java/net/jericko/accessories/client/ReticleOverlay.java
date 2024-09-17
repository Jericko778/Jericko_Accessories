package net.jericko.accessories.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.custom.PistolItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class ReticleOverlay {

    private static final ResourceLocation RETICLE = new ResourceLocation(Accessories.MOD_ID, "textures/item/chaosreticle.png");
    private static boolean focus;

    public static final IGuiOverlay HUD_RETICLE = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        Player player = Minecraft.getInstance().player;
        int x = screenWidth/2;
        int y = screenHeight/2;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,RETICLE);


        if(player != null && player.isHolding(ModItems.CHAOSPISTOL.get()) && !focus){

//            Vec3 pos = player.getEyePosition();
//            Vec3 direction = player.getViewVector(1);
//            List<Mob> hi = player.level().getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x*50, pos.y + direction.y*50, pos.z + direction.z*50));
            int reticleScale = 32;

            guiGraphics.blit(RETICLE, x-reticleScale/2, y-reticleScale/2 +1, 0,0,reticleScale,reticleScale,reticleScale,reticleScale);//(int)(screenWidth*0.1f), (int)(screenHeight*0.2f), (int)(screenWidth*0.1f), (int)(screenHeight*0.2f));

//            for(LivingEntity e:hi){
//            if(e != null && e.canBeSeenByAnyone()){
//                    Entity c = Minecraft.getInstance().cameraEntity;
//                    Vec3 v = c.position().subtract(e.position());
//                    // Gets rid of horizontal scaling
//                    if(player.getForward().normalize().z == 1){
//                        v = new Vec3(v.x*Math.sin(c.getYRot()), v.y, v.z*Math.cos(c.getYRot()));
//                    }
//                    else{
//                        v = new Vec3(v.x*Math.cos(c.getYRot()), v.y, v.z*Math.sin(c.getYRot()));
//                    }
//                    // Gets rid of vertical scaling
//                    v = new Vec3(v.x, v.y * Math.sin(c.getXRot()), v.z*Math.cos(c.getXRot()));
//
//                    int FOV = Minecraft.getInstance().options.fov().get();
//
//                    guiGraphics.blit(BULLET, (int)(v.x/v.z * FOV)+x, (int)(-v.y/ v.z * FOV)+y, 0,0,32,32, 32, 32);
//                    break;
//                }
//                else{
//                    guiGraphics.blit(BULLET, x, y, 0,0,0,0, 0, 0);
//                }
            //}
        }
        else{
            guiGraphics.blit(RETICLE, x-16, y-17, 0,0,0,0, 0, 0);
        }

    }));

    @SubscribeEvent
    public static void StartFocus(InputEvent.Key event){
        Player player = Minecraft.getInstance().player;
        if(player != null && event.getKey() == 340){
            if(event.getAction() == GLFW.GLFW_PRESS){
                focus = true;
            }
            if(event.getAction() == GLFW.GLFW_RELEASE){
                focus = false;
            }
        }
    }

}
