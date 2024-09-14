package net.jericko.accessories.entity.custom;

import net.jericko.accessories.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ReticleEntity extends Display {

    public ReticleEntity(EntityType<?> p_270360_, Level p_270280_) {
        super(p_270360_, p_270280_);
    }

    @Override
    protected void updateRenderSubState(boolean p_277603_, float p_277810_) {

    }
}
