package net.jericko.accessories.item.client;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.ArmItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KnuckleblasterModel extends GeoModel<ArmItem> {
    @Override
    public ResourceLocation getModelResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "geo/knuckleblaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "textures/item/knuckleblaster.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArmItem armItem) {
        return new ResourceLocation(Accessories.MOD_ID, "animations/knuckleblaster.animation.json");
    }
}
