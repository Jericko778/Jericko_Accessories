package net.jericko.accessories.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.custom.ShadesItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BulletOverlay {
    private static final ResourceLocation BULLET_FULL = new ResourceLocation(Accessories.MOD_ID, "textures/item/bars/bulletfull.png");

    private static final ResourceLocation BULLET_EMPTY = new ResourceLocation(Accessories.MOD_ID, "textures/item/bars/bulletempty.png");


    public static final IGuiOverlay BULLET_OVERLAY = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        Player player = Minecraft.getInstance().player;
        int x = screenWidth / 2;
        int y = screenHeight;
        int bulletSize = 12;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, BULLET_FULL);

        if(player.isHolding(ModItems.CHAOSPISTOL.get()) && ShadesItem.isShaded()){
            for (int i = 6; i > 0; i--) {
                if(ClientBulletData.getBullets() < i){
                    guiGraphics.blit(BULLET_EMPTY, x - 100 + (i * 9), y - 50, 0, 0, bulletSize, bulletSize, bulletSize, bulletSize);//(int)(screenWidth*0.1f), (int)(screenHeight*0.2f), (int)(screenWidth*0.1f), (int)(screenHeight*0.2f));
                }
                else{
                    guiGraphics.blit(BULLET_FULL, x - 100 + (i * 9), y - 48, 0, 0, bulletSize, bulletSize, bulletSize, bulletSize);//(int)(screenWidth*0.1f), (int)(screenHeight*0.2f), (int)(screenWidth*0.1f), (int)(screenHeight*0.2f));
                }
            }
        }

    }));

}
