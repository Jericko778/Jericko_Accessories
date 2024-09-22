package net.jericko.accessories.entity.client;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.entity.custom.SpinBallEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CursedBallModel extends GeoModel<SpinBallEntity> {
    @Override
    public ResourceLocation getModelResource(SpinBallEntity animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "geo/cursedball.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpinBallEntity animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "textures/item/cursedball.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpinBallEntity animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "animations/cursedball.animation.json");
    }
}
