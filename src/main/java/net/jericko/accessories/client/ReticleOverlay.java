package net.jericko.accessories.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.List;

public class ReticleOverlay {

    private static final ResourceLocation RETICLE = new ResourceLocation(Accessories.MOD_ID, "textures/item/chaosreticle.png");


    public static final IGuiOverlay HUD_RETICLE = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        Player player = Minecraft.getInstance().player;
        int x = screenWidth/2;
        int y = screenHeight/2;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,RETICLE);


        if(player != null && player.isHolding(ModItems.CHAOSPISTOL.get())){

//            Vec3 pos = player.getEyePosition();
//            Vec3 direction = player.getViewVector(1);
//            List<Mob> hi = player.level().getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x, pos.y, pos.z, pos.x + direction.x*50, pos.y + direction.y*50, pos.z + direction.z*50));

            guiGraphics.blit(RETICLE, x-24, y-25, 0,0,48,48,48,48);//(int)(screenWidth*0.1f), (int)(screenHeight*0.2f), (int)(screenWidth*0.1f), (int)(screenHeight*0.2f));

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

}
