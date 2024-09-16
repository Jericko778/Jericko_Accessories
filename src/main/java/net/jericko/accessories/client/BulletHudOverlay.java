package net.jericko.accessories.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraft.client.gui.GuiGraphics;

public class BulletHudOverlay {

    private static final ResourceLocation BULLET = new ResourceLocation(Accessories.MOD_ID, "textures/item/chaosreticle.png");

    private static final ResourceLocation EMPTY_BULLET = new ResourceLocation(Accessories.MOD_ID, "textures/item/chaosreticle.png");


    public static final IGuiOverlay HUD_BULLETS = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        Player player = Minecraft.getInstance().player;
        int x = screenWidth/2;
        int y = screenHeight/2;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,BULLET);

        if(player != null && player.isHolding(ModItems.CHAOSPISTOL.get())){
            guiGraphics.blit(BULLET, x-16, y-17, 0,0,32,32, 32, 32);
        }
        else{
            guiGraphics.blit(BULLET, x-16, y-16, 0,0,0,0, 0, 0);
        }

    }));

}
