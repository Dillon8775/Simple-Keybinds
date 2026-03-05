package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class ClientEvents {

    /**
     * Registers all keybinds
     */
    public static void registerKeybinds() {
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CLEAR_CHAT);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_MASTER_VOLUME);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.MUTE_GAME);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_BRIGHTNESS);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_GUI_SCALE);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_FOV);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_RENDER_DISTANCE);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.CHANGE_ENTITY_DISTANCE);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.PAUSE_WITHOUT_MENU);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.RELOAD_CHUNKS);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.SHOW_ADVANCED_TOOLTIPS);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.TOGGLE_CHUNK_BORDERS);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.TOGGLE_FOG);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.TOGGLE_FULLBRIGHT);
        KeyMappingHelper.registerKeyMapping(ModKeybinds.TOGGLE_HITBOXES);
    }
}