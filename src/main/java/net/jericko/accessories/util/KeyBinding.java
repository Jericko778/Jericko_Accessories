package net.jericko.accessories.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ACCESSORIES = "key.category.accessories.accessories";
    public static final String KEY_DASH = "key.accessories.dash";
    public static final KeyMapping DASH = new KeyMapping(KEY_DASH, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, KEY_CATEGORY_ACCESSORIES);
}
