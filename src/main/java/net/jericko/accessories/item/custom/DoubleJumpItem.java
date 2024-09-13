package net.jericko.accessories.item.custom;

import net.jericko.accessories.Accessories;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class DoubleJumpItem extends Item implements ICurioItem {
    public static int uses;
    public DoubleJumpItem(Properties p_41383_) {
        super(p_41383_);
        this.uses = 0;
    }

    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if(uses == 0 && slotContext.entity().onGround()){
            uses = 1;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        p_41432_.playSound(null, p_41433_.blockPosition(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);

        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        uses = 0;
    }

    @SubscribeEvent
    public static void doubleJump(InputEvent.Key event){
        Player player = Minecraft.getInstance().player;

        if(player != null && !player.onGround() && uses > 0 && event.getKey() == 32 && event.getAction() == GLFW.GLFW_PRESS){
            uses--;

            Vec3 vec3 = player.getDeltaMovement();
            player.setDeltaMovement(vec3.x, (0.42F + player.getJumpBoostPower()), vec3.z);
            if (player.isSprinting()) {
                float f = player.getYRot() * 0.017453292F;
                player.setDeltaMovement(player.getDeltaMovement().add((-Mth.sin(f) * 0.2F), 0.0, (Mth.cos(f) * 0.2F)));
            }
       }
    }


}
