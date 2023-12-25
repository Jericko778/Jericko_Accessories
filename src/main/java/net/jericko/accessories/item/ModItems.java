package net.jericko.accessories.item;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.DashItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Accessories.MOD_ID);

    //items
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("tests", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEST_RAW = ITEMS.register("raw_tests", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DASH = ITEMS.register("dash", () -> new DashItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
