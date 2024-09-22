package net.jericko.accessories.item.client;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.ShadesItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ChaosShadesModel extends GeoModel<ShadesItem>{
    @Override
    public ResourceLocation getModelResource(ShadesItem animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "geo/chaosshades.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadesItem animatable) {
        return new ResourceLocation(Accessories.MOD_ID, "textures/item/glasses_orange.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadesItem animatable) {
        return null;
    }
}
