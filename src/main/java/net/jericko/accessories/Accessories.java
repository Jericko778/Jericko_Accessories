package net.jericko.accessories;

import com.mojang.logging.LogUtils;
import net.jericko.accessories.block.ModBlocks;
import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.entity.client.CursedBallRenderer;
import net.jericko.accessories.item.ModCreativeModeTabs;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Accessories.MOD_ID)
public class Accessories
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "accessories";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static ResourceLocation id(String path, String... args) {
        return new ResourceLocation(MOD_ID, String.format(path, (Object[]) args));
    }

    public Accessories()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
        }

        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
        }

        if(event.getTab() == ModCreativeModeTabs.ACCESSORY_TAB.get()){
            event.accept(ModItems.DASH);
            event.accept(ModItems.BOOTS);
            event.accept(ModItems.MIRROR);
            event.accept(ModItems.CLOUD_IN_A_BOTTLE);
            event.accept(ModItems.KNUCKLEBLASTER);
            event.accept(ModItems.CHAOSPISTOL);
            event.accept(ModItems.CURSEDBALL);
            event.accept(ModItems.CHAOSSHADES);
            event.accept(ModItems.MIMICRYGIFT);
            event.accept(ModItems.SMILEGIFT);
        }
        if(event.getTab() == ModCreativeModeTabs.GUILTYGEAR_TAB.get()){
            event.accept(ModItems.CHAOSPISTOL);
            event.accept(ModItems.CHAOSSHADES);
        }
    }



    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CHAOSFOCUSRETICLE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.CURSEDBALL.get(), CursedBallRenderer::new);
            //CuriosRendererRegistry.register(ModItems.CHAOSSHADES.get(), (Supplier<ICurioRenderer>) new ChaosShadesCurioRenderer("chaosshades", new ChaosShadesModel(ArtifactRenderers.bakeLayer(new ModelLayerLocation(Accessories.id("models/item/chaosshades.json"), "chaosshades")))));
        }
    }
}
