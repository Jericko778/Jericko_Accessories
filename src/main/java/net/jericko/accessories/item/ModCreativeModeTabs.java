package net.jericko.accessories.item;

import net.jericko.accessories.Accessories;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Accessories.MOD_ID);
    public static RegistryObject<CreativeModeTab> ACCESSORY_TAB = CREATIVE_MODE_TABS.register("accessory_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.KNUCKLEBLASTER.get()))
                    .title(Component.translatable("creativemodetab.accessory_tab")).build());
    public static RegistryObject<CreativeModeTab> GUILTYGEAR_TAB = CREATIVE_MODE_TABS.register("guilty_gear_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHAOSPISTOL.get()))
                    .title(Component.translatable("creativemodetab.guilty_gear_tab")).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
