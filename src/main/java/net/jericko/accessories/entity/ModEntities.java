package net.jericko.accessories.entity;

import net.jericko.accessories.Accessories;
import net.jericko.accessories.entity.custom.ReticleEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Accessories.MOD_ID);


    public static final RegistryObject<EntityType<ReticleEntity>> CHAOSRETICLE =
            ENTITY_TYPES.register("reticle", () -> EntityType.Builder.<ReticleEntity>of(ReticleEntity::new, MobCategory.MISC)
                    .sized(0.01f,0.01f).build("reticle"));


    public static void register(IEventBus eventBus) { ENTITY_TYPES.register(eventBus);}

}
