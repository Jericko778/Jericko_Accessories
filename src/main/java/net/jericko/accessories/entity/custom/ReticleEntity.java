package net.jericko.accessories.entity.custom;

import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class ReticleEntity extends ThrowableItemProjectile {
    public ReticleEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ReticleEntity(Level level) {
        super(ModEntities.CHAOSFOCUSRETICLE.get(), level);
    }

    public ReticleEntity(Level level, LivingEntity livingEntity) {
        super(ModEntities.CHAOSFOCUSRETICLE.get(), livingEntity, level);
    }

    @Override
    public void tick() {
        Player player = Minecraft.getInstance().player;
        if(player != null){
            this.setPos(player.getEyePosition().add(player.getViewVector(1).multiply(2,2,2)));
            super.tick();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOSFOCUSRETICLE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if(this.level().isClientSide()){
            this.level().broadcastEntityEvent(this, (byte)(3));
        }
        super.onHitBlock(blockHitResult);
    }
}
