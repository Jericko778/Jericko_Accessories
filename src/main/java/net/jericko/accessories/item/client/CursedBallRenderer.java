package net.jericko.accessories.item.client;

import net.jericko.accessories.item.custom.ArmItem;
import net.jericko.accessories.item.custom.SpinBallItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CursedBallRenderer extends GeoItemRenderer<SpinBallItem> {

    public CursedBallRenderer() {
        super(new CursedBallModel());
    }
}
