package net.jericko.accessories.entity;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.entity.custom.ReticleEntity;
import net.jericko.accessories.item.custom.PistolItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Accessories.MOD_ID);

    //public static final RegistryObject<EntityType<ReticleEntity>> CHAOSRETICLE = ENTITY_TYPES.register("chaosreticle", () -> EntityType.Builder.of(ReticleEntity::new, MobCategory.MISC).sized(3,3).build(new ResourceLocation(Accessories.MOD_ID, "chaosreticle").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}
