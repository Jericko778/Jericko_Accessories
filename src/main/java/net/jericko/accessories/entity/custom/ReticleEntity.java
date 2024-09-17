package net.jericko.accessories.entity.custom;

import net.jericko.accessories.entity.ModEntities;
import net.jericko.accessories.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.apache.http.util.EntityUtils;
import org.lwjgl.opengl.GL11;

import java.util.List;

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
//        Player player = Minecraft.getInstance().player;
//        if(player != null){
//            Vec3 pos = player.position();
//            List<Mob> hi = player.level().getNearbyEntities(Mob.class, TargetingConditions.DEFAULT, player, new AABB(pos.x-50, pos.y-50, pos.z-50, pos.x + 50, pos.y +50, pos.z + 50));
//            Entity e = player.level().getNearestEntity(hi, TargetingConditions.DEFAULT, player, 50, 50, 50);
//            this.setPos(player.position().add((e.position().subtract(player.position())).multiply(0.2,0.2,0.2)));
//            //this.setPos(player.getEyePosition().add(player.getViewVector(1)));
//
//            super.tick();
//        }
        super.tick();
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
