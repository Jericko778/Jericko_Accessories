package net.jericko.accessories.item.custom;

import net.jericko.accessories.item.ModItems;
import net.jericko.accessories.item.client.ChaosShadesRenderer;
import net.jericko.accessories.item.client.CrescentMoonRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.util.RenderUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.function.Consumer;

public class ShadesItem extends Item implements GeoItem, ICurioItem, Equipable {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private ItemStack currentHead;

    public ShadesItem(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        Player player = Minecraft.getInstance().player;
        if(!slotContext.visible()){
            player.setItemSlot(EquipmentSlot.HEAD, currentHead);
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        Player player = Minecraft.getInstance().player;
        player.setItemSlot(EquipmentSlot.HEAD, currentHead);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(itemStack, level, entity, i, b);
        Player player = Minecraft.getInstance().player;
        if(!player.getItemBySlot(EquipmentSlot.HEAD).getDisplayName().equals(itemStack.getDisplayName())){
            currentHead = player.getItemBySlot(EquipmentSlot.HEAD);
        }
//        if(player.equipmentHasChanged(itemStack, player.getItemBySlot(EquipmentSlot.HEAD))){
//            currentHead = player.getItemBySlot(EquipmentSlot.HEAD);
//        }
//        player.onEquipItem(EquipmentSlot.HEAD, itemStack, player.getItemBySlot(EquipmentSlot.HEAD));


    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ChaosShadesRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    renderer = new ChaosShadesRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
