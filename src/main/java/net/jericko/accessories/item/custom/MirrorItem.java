package net.jericko.accessories.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;

public class MirrorItem extends Item {
    public MirrorItem(Properties p_41383_) {
        super(p_41383_);
    }
/*TODO: Make it so that it doesn't take fall damage if falling
* TODO: Add particles
*  TODO: Teleport animals if riding*/
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand p_41434_) {
        if (!level.isClientSide()) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            BlockPos spawn = serverPlayer.getRespawnPosition();
            ServerLevel overworld = serverPlayer.getServer().overworld();
            ServerLevel end = serverPlayer.getServer().getLevel(Level.END);

            if(level.equals(end)){
                Iterable<Entity> endEntities = end.getAllEntities();
                for(Entity entity : endEntities){
                    if(entity instanceof EnderDragon){
                        return InteractionResultHolder.fail(player.getItemInHand(p_41434_));
                    }
                }
            }

            if(!(overworld.getBlockState(spawn).getBlock() instanceof BedBlock)) {
                spawn = level.getSharedSpawnPos();
            }
            if(player.isPassenger()){
                player.stopRiding();
            }
            serverPlayer.setDeltaMovement(0,0,0);
            if(!level.equals(overworld)){
                player.teleportTo(overworld, spawn.getX(), spawn.getY(), spawn.getZ(), null, 0f, 0f);
            }else {
                player.teleportTo(spawn.getX(), spawn.getY(), spawn.getZ());
            }
            player.setPose(Pose.STANDING);
            player.setDeltaMovement(0,0,0);
            level.playSound(null, spawn, SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getItemInHand(p_41434_).hurtAndBreak(1, player, play -> play.broadcastBreakEvent(p_41434_));
            player.getCooldowns().addCooldown(this, 100);
        }

        return InteractionResultHolder.pass(player.getItemInHand(p_41434_));
    }

    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack ingredient) {
        return Ingredient.of(Items.DIAMOND).test(ingredient);
    }

}
