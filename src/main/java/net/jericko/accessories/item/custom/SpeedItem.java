package net.jericko.accessories.item.custom;

import com.sun.jdi.connect.spi.TransportService;
import com.sun.jna.platform.win32.WinBase;
import net.jericko.accessories.Accessories;
import net.jericko.accessories.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mod.EventBusSubscriber(modid = Accessories.MOD_ID)
public class SpeedItem extends BuffAccessory implements ICurioItem {
    //TODO: Add particles
    public SpeedItem(Properties properties, Attribute[] attribute, int[] born, double[] amount) {
        super(properties, attribute, born, amount);
    }
    private Level lightning = null;

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = (Player) slotContext.entity();
        lightning = slotContext.entity().level();

        if (player.isShiftKeyDown() && !lightning.isClientSide()) {
            if (!player.getCooldowns().isOnCooldown(this.asItem())) {
                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, lightning);
                bolt.setVisualOnly(true);
                bolt.setPos(player.position());
                lightning.addFreshEntity(bolt);
                player.removeEffect(MobEffects.MOVEMENT_SPEED);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 5));
                player.getCooldowns().addCooldown(this.asItem(), 500);
            }
        }
    }
}