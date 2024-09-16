package net.jericko.accessories.item.client;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.ArmItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CursedBallModel extends GeoModel<ArmItem> {
    @Override
    public ResourceLocation getModelResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "geo/cursedball.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "textures/item/cursedball.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "animations/cursedball.animation.json");
    }
}
