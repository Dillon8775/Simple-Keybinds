package net.dillon.simplekeybinds.keybinds;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModKeybinds {
    private static final String MOD_KEYBINDS = "simplekeybinds.keybinds";
    public static KeyBinding fogKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, MOD_KEYBINDS));
    public static KeyBinding fullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));
    public static KeyBinding hitboxesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, MOD_KEYBINDS));
    public static KeyBinding chunkBordersKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, MOD_KEYBINDS));

    public static void init() {
        SimpleKeybinds.LOGGER.info("Initialized Simple Keybinds.");
    }
}