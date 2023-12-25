package net.jericko.accessories;

import com.mojang.logging.LogUtils;
import net.jericko.accessories.block.ModBlocks;
import net.jericko.accessories.item.ModCreativeModeTabs;
import net.jericko.accessories.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Accessories.MOD_ID)
public class Accessories
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "accessories";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public Accessories()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        //curios
        modEventBus.addListener(this::enqueue);
    }

    private void enqueue(final InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.CURIO.getMessageBuilder().build());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.TEST_ITEM);
            event.accept(ModItems.TEST_RAW);
        }

        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.TEST_BLOCK);
        }

        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.TEST_ORE);
            event.accept(ModBlocks.DEEPSLATE_TEST_ORE);
        }

        if(event.getTab() == ModCreativeModeTabs.ACCESSORY_TAB){
            event.accept(ModItems.TEST_ITEM);
            event.accept(ModItems.TEST_RAW);
            event.accept(ModBlocks.TEST_BLOCK);
            event.accept(ModBlocks.TEST_ORE);
            event.accept(ModBlocks.DEEPSLATE_TEST_ORE);
            event.accept(ModItems.DASH);
        }
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
