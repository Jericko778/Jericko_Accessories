package net.jericko.accessories.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ACCESSORIES = "key.category.accessories.accessories";
    public static final String KEY_FIRST_SLOT = "key.accessories.first_slot";
    public static final KeyMapping FIRST_SLOT = new KeyMapping(KEY_FIRST_SLOT, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATEGORY_ACCESSORIES);
}
