package net.jericko.accessories.item.custom;

import net.jericko.accessories.Accessories;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class DoubleJumpItem extends Item implements ICurioItem {
    private int uses;
    private boolean released;
    public DoubleJumpItem(Properties p_41383_) {
        super(p_41383_);
        this.uses = 0;
        released = true;
    }

    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player != null;
        if ((player.onGround() || player.onClimbable()) && (!player.isInWater())) {
            released = false;
            uses = 1;
        } else if (!player.input.jumping) {
            released = true;
        } else if (!player.getAbilities().flying && uses > 0 && released) {
            this.doubleJump(player);
            uses = 0;
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, @NotNull InteractionHand p_41434_) {
        p_41432_.playSound(null, p_41433_.blockPosition(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);

        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        uses = 0;
    }

    public void doubleJump(Player player){
        this.uses--;

            Vec3 vec3 = player.getDeltaMovement();
            player.setDeltaMovement(vec3.x, (0.42F + player.getJumpBoostPower()), vec3.z);
            if (player.isSprinting()) {
                float f = player.getYRot() * 0.017453292F;
                player.setDeltaMovement(player.getDeltaMovement().add((-Mth.sin(f) * 0.2F), 0.0, (Mth.cos(f) * 0.2F)));
            }
    }
}
