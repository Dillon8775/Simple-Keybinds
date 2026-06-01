package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.dillon.simplekeybinds.util.ModUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModUtil.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {

    /**
     * Registers all keybinds.
     */
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeybinds.CLEAR_CHAT);
        event.register(ModKeybinds.CHANGE_MASTER_VOLUME);
        event.register(ModKeybinds.MUTE_GAME);
        event.register(ModKeybinds.CHANGE_BRIGHTNESS);
        event.register(ModKeybinds.CHANGE_GUI_SCALE);
        event.register(ModKeybinds.CHANGE_FOV);
        event.register(ModKeybinds.CHANGE_RENDER_DISTANCE);
        event.register(ModKeybinds.CHANGE_ENTITY_DISTANCE);
        event.register(ModKeybinds.PAUSE_WITHOUT_MENU);
        event.register(ModKeybinds.RELOAD_CHUNKS);
        event.register(ModKeybinds.SHOW_ADVANCED_TOOLTIPS);
        event.register(ModKeybinds.TOGGLE_CHUNK_BORDERS);
        event.register(ModKeybinds.TOGGLE_FOG);
        event.register(ModKeybinds.TOGGLE_FULLBRIGHT);
        event.register(ModKeybinds.TOGGLE_HITBOXES);
        event.register(ModKeybinds.QUICK_EXIT);
    }
}