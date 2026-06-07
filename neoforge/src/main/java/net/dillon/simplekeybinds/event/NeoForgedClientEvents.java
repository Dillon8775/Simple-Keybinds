package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = ModHelper.MOD_ID, value = Dist.CLIENT)
public class NeoForgedClientEvents {

    /**
     * Registers all keybinds.
     */
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        ModKeyMappings.initKeybinds();

        event.register(ModKeyMappings.CHANGE_MASTER_VOLUME);
        event.register(ModKeyMappings.CHANGE_BRIGHTNESS);
        event.register(ModKeyMappings.CHANGE_GUI_SCALE);
        event.register(ModKeyMappings.CHANGE_FOV);
        event.register(ModKeyMappings.CHANGE_RENDER_DISTANCE);
        event.register(ModKeyMappings.CHANGE_ENTITY_DISTANCE);
    }
}