package net.jericko.accessories.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.realmsclient.dto.PlayerInfo;
import net.jericko.accessories.event.ClientEvents;
import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.custom.ShadesItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.jar.Attributes;

public class ChaosShadesRenderer extends GeoItemRenderer<ShadesItem> implements ICurioRenderer{
    public ChaosShadesRenderer() {
        super(new ChaosShadesModel());
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStack.pushPose();
        Player player = Minecraft.getInstance().player;
        matrixStack.scale(-0.6f,-0.6f,0.6f);
        matrixStack.translate(0,0.5f,0.5f);
        Minecraft.getInstance().getItemRenderer().renderStatic(ModItems.CHAOSSHADES.get().getDefaultInstance(), ItemDisplayContext.HEAD, 255,255, matrixStack, renderTypeBuffer, slotContext.entity().level(), 0);
        matrixStack.popPose();
    }
}
