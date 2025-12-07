package net.dillon.simplekeybinds.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = SimpleKeybinds.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeybinds {
    private static final String MOD_KEYBINDS = "simplekeybinds.keybinds";

    public static final KeyMapping CLEAR_CHAT = new KeyMapping(
            "simplekeybinds.clear_chat",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_MASTER_VOLUME = new KeyMapping(
            "simplekeybinds.change_master_volume",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_BRIGHTNESS = new KeyMapping(
            "simplekeybinds.change_brightness",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_GUI_SCALE = new KeyMapping(
            "simplekeybinds.change_gui_scale",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_FOV = new KeyMapping(
            "simplekeybinds.change_fov",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_RENDER_DISTANCE = new KeyMapping(
            "simplekeybinds.change_render_distance",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_U,
            MOD_KEYBINDS
    );

    public static final KeyMapping CHANGE_ENTITY_DISTANCE = new KeyMapping(
            "simplekeybinds.change_entity_distance",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            MOD_KEYBINDS
    );

    public static final KeyMapping PAUSE_WITHOUT_MENU = new KeyMapping(
            "simplekeybinds.pause_without_menu",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            MOD_KEYBINDS
    );

    public static final KeyMapping RELOAD_CHUNKS = new KeyMapping(
            "simplekeybinds.reload_chunks",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            MOD_KEYBINDS
    );

    public static final KeyMapping SHOW_ADVANCED_TOOLTIPS = new KeyMapping(
            "simplekeybinds.show_advanced_tooltips",
            InputConstants.Type.KEYSYM,
            InputConstants.UNKNOWN.getValue(),
            MOD_KEYBINDS
    );

    public static final KeyMapping TOGGLE_CHUNK_BORDERS = new KeyMapping(
            "simplekeybinds.toggle_chunk_borders",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            MOD_KEYBINDS
    );

    public static final KeyMapping TOGGLE_FOG = new KeyMapping(
            "simplekeybinds.toggle_fog",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            MOD_KEYBINDS
    );

    public static final KeyMapping TOGGLE_FULLBRIGHT = new KeyMapping(
            "simplekeybinds.toggle_fullbright",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            MOD_KEYBINDS
    );

    public static final KeyMapping TOGGLE_HITBOXES = new KeyMapping(
            "simplekeybinds.toggle_hitboxes",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            MOD_KEYBINDS
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(ModKeybinds.CLEAR_CHAT);
        event.register(ModKeybinds.CHANGE_MASTER_VOLUME);
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
    }
}