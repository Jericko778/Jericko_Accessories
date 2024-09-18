package net.jericko.accessories.item.client;

import net.jericko.accessories.item.custom.PistolItem;
import net.jericko.accessories.item.custom.SpinBallItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CrescentMoonRenderer extends GeoItemRenderer<PistolItem> {
    public CrescentMoonRenderer() {
        super(new CrescentMoonModel());
    }
}
