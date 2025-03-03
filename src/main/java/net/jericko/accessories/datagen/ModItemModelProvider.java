package net.jericko.accessories.datagen;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Accessories.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.DASH);
        handheldItem(ModItems.MIRROR);
        simpleItem(ModItems.CLOUD_IN_A_BOTTLE);
        simpleItem(ModItems.BOOTS);
        simpleItem(ModItems.CHAOSFOCUSRETICLE);
        simpleItem(ModItems.MIMICRYGIFT);
        simpleItem(ModItems.SMILEGIFT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Accessories.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Accessories.MOD_ID,"item/" + item.getId().getPath()));
    }
}
