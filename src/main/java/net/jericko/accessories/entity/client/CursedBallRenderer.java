package net.jericko.accessories.entity.client;

import net.jericko.accessories.entity.custom.SpinBallEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CursedBallRenderer extends GeoEntityRenderer<SpinBallEntity> {

    public CursedBallRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CursedBallModel());
    }
}
