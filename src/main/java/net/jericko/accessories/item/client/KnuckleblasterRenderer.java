package net.jericko.accessories.item.client;

import net.jericko.accessories.item.custom.ArmItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KnuckleblasterRenderer extends GeoItemRenderer<ArmItem> {

    public KnuckleblasterRenderer() {
        super(new KnuckleblasterModel());
    }
}
