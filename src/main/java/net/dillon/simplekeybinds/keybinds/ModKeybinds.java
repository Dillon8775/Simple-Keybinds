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

    public static final KeyBinding CLEAR_CHAT = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.clear_chat", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));
    public static final KeyBinding DECREASE_BRIGHTNESS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.decrease_brightness", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, MOD_KEYBINDS));
    public static final KeyBinding INCREASE_BRIGHTNESS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.increase_brightness", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, MOD_KEYBINDS));
    public static final KeyBinding PAUSE_WITHOUT_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.pause_without_menu", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));
    public static final KeyBinding RELOAD_CHUNKS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.reload_chunks", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));
    public static final KeyBinding SHOW_ADVANCED_TOOLTIPS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.show_advanced_tooltips", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));
    public static final KeyBinding TOGGLE_CHUNK_BORDERS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, MOD_KEYBINDS));
    public static final KeyBinding TOGGLE_FOG = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, MOD_KEYBINDS));
    public static final KeyBinding TOGGLE_FULLBRIGHT = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, MOD_KEYBINDS));
    public static final KeyBinding TOGGLE_HITBOXES = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, MOD_KEYBINDS));

    public static void init() {
        SimpleKeybinds.LOGGER.info("Simple Keybinds mod has been loaded.");
    }
}