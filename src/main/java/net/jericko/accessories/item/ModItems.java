package net.jericko.accessories.item;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.custom.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Accessories.MOD_ID);

    //items
    public static final RegistryObject<Item> DASH = ITEMS.register("dash", () -> new DashItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BOOTS = ITEMS.register("boots", () -> new SpeedItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MIRROR = ITEMS.register("mirror", () -> new MirrorItem(new Item.Properties().stacksTo(1).durability(100)));
    public static final RegistryObject<Item> CLOUD_IN_A_BOTTLE = ITEMS.register("cloud_in_a_bottle", () -> new DoubleJumpItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> KNUCKLEBLASTER = ITEMS.register("knuckleblaster", () -> new ArmItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHAOSPISTOL = ITEMS.register("crescentmoon", () -> new PistolItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHAOSFOCUSRETICLE = ITEMS.register("chaosfocusreticle", () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CURSEDBALL = ITEMS.register("cursedball", () -> new SpinBallItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHAOSSHADES = ITEMS.register("chaosshades", () -> new ShadesItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MIMICRYGIFT = ITEMS.register("mimicry_gift", () -> new MimicryGift(new Item.Properties().stacksTo(1), Attributes.MAX_HEALTH, 1));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
