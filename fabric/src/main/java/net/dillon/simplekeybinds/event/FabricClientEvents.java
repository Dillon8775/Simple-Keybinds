package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class FabricClientEvents {

    /**
     * Registers all keybinds
     */
    public static void registerKeybinds() {
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CLEAR_CHAT);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_MASTER_VOLUME);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.MUTE_GAME);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_BRIGHTNESS);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_GUI_SCALE);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_FOV);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_RENDER_DISTANCE);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.CHANGE_ENTITY_DISTANCE);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.PAUSE_WITHOUT_MENU);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.RELOAD_CHUNKS);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.SHOW_ADVANCED_TOOLTIPS);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.TOGGLE_CHUNK_BORDERS);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.TOGGLE_FOG);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.TOGGLE_FULLBRIGHT);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.TOGGLE_HITBOXES);
        KeyBindingHelper.registerKeyBinding(ModKeybinds.QUICK_EXIT);
    }
}