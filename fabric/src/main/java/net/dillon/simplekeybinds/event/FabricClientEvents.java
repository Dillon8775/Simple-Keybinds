package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class FabricClientEvents {

    /**
     * Registers all keybinds
     */
    public static void registerKeybinds() {
        ModKeyMappings.initKeybinds();

        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_MASTER_VOLUME);
        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_BRIGHTNESS);
        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_GUI_SCALE);
        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_FOV);
        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_RENDER_DISTANCE);
        KeyMappingHelper.registerKeyMapping(ModKeyMappings.CHANGE_ENTITY_DISTANCE);
    }
}