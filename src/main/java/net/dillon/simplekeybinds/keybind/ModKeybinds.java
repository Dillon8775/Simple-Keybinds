package net.dillon.simplekeybinds.keybind;

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

    /**
     * Clears the players chat.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding CLEAR_CHAT = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.clear_chat", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Increases/decreases the players gamma by {@code 50%.}
     * <p>{@code Default Key = J}</p>
     */
    public static final KeyBinding CHANGE_BRIGHTNESS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.change_brightness", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, MOD_KEYBINDS));

    /**
     * Increase/decreases the players render distance by {@code 1.}
     * <p>{@code Default Key = U}</p>
     */
    public static final KeyBinding CHANGE_RENDER_DISTANCE = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.change_render_distance", GLFW.GLFW_KEY_U, MOD_KEYBINDS));

    /**
     * Increases/decreases entity distance by {@code 25%.}
     * <p>{@code Default Key = H}</p>
     */
    public static final KeyBinding CHANGE_ENTITY_DISTANCE = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.change_entity_distance", GLFW.GLFW_KEY_H, MOD_KEYBINDS));

    /**
     * Pauses the game, without the actual menu displaying.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding PAUSE_WITHOUT_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.pause_without_menu", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Reloads all chunks.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding RELOAD_CHUNKS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.reload_chunks", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Shows advanced or additional tooltips on items.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding SHOW_ADVANCED_TOOLTIPS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.show_advanced_tooltips", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Toggles the F3 menu.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding TOGGLE_DEBUG_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_debug_menu", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Toggles the players HUD menu, such has hotbar display and vignette.
     * <p>{@code No default key assigned.}</p>
     */
    public static final KeyBinding TOGGLE_HUD = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_hud", InputUtil.UNKNOWN_KEY.getCode(), MOD_KEYBINDS));

    /**
     * Toggles chunk borders.
     * <p>{@code Default Key = Y}</p>
     */
    public static final KeyBinding TOGGLE_CHUNK_BORDERS = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, MOD_KEYBINDS));

    /**
     * Toggles Minecraft's fog on/off.
     * <p>{@code Default Key = O}</p>
     */
    public static final KeyBinding TOGGLE_FOG = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, MOD_KEYBINDS));

    /**
     * Toggles fullbright (or very high gamma).
     * <p>{@code Default Key = V}</p>
     */
    public static final KeyBinding TOGGLE_FULLBRIGHT = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, MOD_KEYBINDS));

    /**
     * Toggles all hitboxes.
     * <p>{@code Default Key = X}</p>
     */
    public static final KeyBinding TOGGLE_HITBOXES = KeyBindingHelper.registerKeyBinding(new KeyBinding("simplekeybinds.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, MOD_KEYBINDS));

    public static void init() {
        SimpleKeybinds.LOGGER.info("Simple Keybinds core has been loaded.");
    }
}