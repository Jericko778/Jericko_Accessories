package net.jericko.accessories.item.client;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.PistolItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CrescentMoonModel extends GeoModel<PistolItem> {

    @Override
    public ResourceLocation getModelResource(PistolItem animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "geo/crescentmoon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PistolItem animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "textures/item/pistoltexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PistolItem animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "animations/crescentmoon.animation.json");
    }
}
