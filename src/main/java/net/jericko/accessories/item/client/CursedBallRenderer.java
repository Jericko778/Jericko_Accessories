package net.jericko.accessories.item.client;

import net.jericko.accessories.item.custom.ArmItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CursedBallRenderer extends GeoItemRenderer<ArmItem> {

    public CursedBallRenderer() {
        super(new CursedBallModel());
    }
}
