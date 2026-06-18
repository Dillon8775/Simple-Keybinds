package net.dillon.simplekeybinds.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.*;
import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.option.ModOptions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.simplekeybinds.helper.ModHelper.*;

/**
 * All Simple Keybinds.
 */
public class ModKeyMappings {
    public static final KeyMapping.Category DEFAULT_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("simplekeybinds", "simplekeybinds"));
    private static final KeyMapping.Category SCROLLING_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("simplekeybinds", "simplekeybinds.scrolling"));


    private static final ManagedKeyMapping MUTE_GAME = Kuma.createKeyMapping(ofSimpleKeybinds("mute"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_M, KeyModifiers.of(KeyModifier.CONTROL)))
            .handleWorldInput(handler -> {
                MuteCallback.resetMuteCooldown();
                Options options = Minecraft.getInstance().options;

                if (!muted) {
                    mute(options);
                } else {
                    unmute(options);
                }

                LocalPlayer player = Minecraft.getInstance().player;
                if (!scrolling && player != null) {
                    sendClientMessage(player, muted
                            ? Component.translatable("simplekeybinds.muted").withStyle(ChatFormatting.RED)
                            : Component.translatable("simplekeybinds.unmuted").withStyle(ChatFormatting.GREEN));
                }

                Minecraft.getInstance().options.save();
                return true;
            })
            .build();

    private static final ManagedKeyMapping CLEAR_CHAT = Kuma.createKeyMapping(ofSimpleKeybinds("clear_chat"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                getChatHud().clearMessages(false);
                return true;
            })
            .build();

    private static final ManagedKeyMapping PAUSE_WITHOUT_MENU = Kuma.createKeyMapping(ofSimpleKeybinds("pause_without_menu"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                Minecraft.getInstance().setScreen(new PauseScreen(false));
                return true;
            })
            .build();

    private static final ManagedKeyMapping RELOAD_CHUNKS = Kuma.createKeyMapping(ofSimpleKeybinds("reload_chunks"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                Minecraft.getInstance().levelRenderer.allChanged();
                getChatHud().addClientSystemMessage(message("debug.reload_chunks.message"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping RESET_BRIGHTNESS = Kuma.createKeyMapping(ofSimpleKeybinds("reset_brightness"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_V, KeyModifiers.of(KeyModifier.ALT)))
            .handleWorldInput(handler -> {
                if (autoBrightness()) {
                    return false;
                }
                Minecraft.getInstance().options.gamma().set(1.0D);
                getChatHud().addClientSystemMessage(message("simplekeybinds.reset_brightness"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping SHOW_ADVANCED_TOOLTIPS = Kuma.createKeyMapping(ofSimpleKeybinds("show_advanced_tooltips"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                Minecraft.getInstance().options.advancedItemTooltips = !Minecraft.getInstance().options.advancedItemTooltips;
                getChatHud().addClientSystemMessage(message(Minecraft.getInstance().options.advancedItemTooltips ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping TOGGLE_CHUNK_BORDERS = Kuma.createKeyMapping(ofSimpleKeybinds("toggle_chunk_borders"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_B))
            .handleWorldInput(handler -> {
                boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.CHUNK_BORDERS);
                getChatHud().addClientSystemMessage(message(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping TOGGLE_FOG = Kuma.createKeyMapping(ofSimpleKeybinds("toggle_fog"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_O))
            .handleWorldInput(handler -> {
                if (qualityOfQuesoLoaded()) {
                    getChatHud().addClientSystemMessage(message("simplekeybinds.options.fog.disabled"));
                    return false;
                }
                options().fog = !options().fog;
                ModOptions.saveConfig();
                Minecraft.getInstance().levelRenderer.allChanged();
                getChatHud().addClientSystemMessage(message(options().fog ? "simplekeybinds.fog.on" : "simplekeybinds.fog.off"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping TOGGLE_FULLBRIGHT = Kuma.createKeyMapping(ofSimpleKeybinds("toggle_fullbright"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_V))
            .handleWorldInput(handler -> {
                if (autoBrightness() || RESET_BRIGHTNESS.areModifiersActive()) {
                    return false;
                }
                double currentBrightness = Minecraft.getInstance().options.gamma().get();
                if (!ModHelper.fullBright) {
                    ModHelper.previousBrightness = currentBrightness;
                    if (currentBrightness >= 8.0D) {
                        ModHelper.previousBrightness = 1.0D;
                    }
                }
                ModHelper.fullBright = !ModHelper.fullBright;
                Minecraft.getInstance().options.gamma().set(ModHelper.fullBright ? ModHelper.maxBrightness : ModHelper.previousBrightness);
                getChatHud().addClientSystemMessage(message(ModHelper.fullBright ? "simplekeybinds.fullbright.on" : "simplekeybinds.fullbright.off"));
                return true;
            })
            .build();

    private static final ManagedKeyMapping TOGGLE_HITBOXES = Kuma.createKeyMapping(ofSimpleKeybinds("toggle_hitboxes"))
            .overrideCategory(DEFAULT_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_X))
            .handleWorldInput(handler -> {
                boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                getChatHud().addClientSystemMessage(message(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off"));
                return true;
            })
            .build();

    /**
     * Added By Wheeple.
     */
    private static final ManagedKeyMapping QUICK_EXIT = Kuma.createKeyMapping(ofSimpleKeybinds("quick_exit"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                Minecraft.getInstance().stop();
                return true;
            })
            .handleScreenInput(handler -> {
                Minecraft.getInstance().stop();
                return true;
            })
            .build();

    public static final KeyMapping CHANGE_MASTER_VOLUME = new KeyMapping(
            "simplekeybinds.change_master_volume",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            SCROLLING_CATEGORY,
            1
    );

    public static final KeyMapping CHANGE_RENDER_DISTANCE = new KeyMapping(
            "simplekeybinds.change_render_distance",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_U,
            SCROLLING_CATEGORY,
            2
    );

    public static final KeyMapping CHANGE_ENTITY_DISTANCE = new KeyMapping(
            "simplekeybinds.change_entity_distance",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            SCROLLING_CATEGORY,
            3
    );

    public static final KeyMapping CHANGE_BRIGHTNESS = new KeyMapping(
            "simplekeybinds.change_brightness",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            SCROLLING_CATEGORY,
            4
    );

    public static final KeyMapping CHANGE_FOV = new KeyMapping(
            "simplekeybinds.change_fov",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            SCROLLING_CATEGORY,
            5
    );

    public static final KeyMapping CHANGE_GUI_SCALE = new KeyMapping(
            "simplekeybinds.change_gui_scale",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            SCROLLING_CATEGORY,
            6
    );

    /**
     * @return if auto brightness is enabled, and sends a message to the player.
     */
    public static boolean autoBrightness() {
        if (options().autoBrightness) {
            getChatHud().addClientSystemMessage(Component.translatable("simplekeybinds.cannot_change_brightness").withStyle(ChatFormatting.RED));
            return true;
        }
        return false;
    }

    /**
     * Sends a message to the player and saves the Minecraft options.
     */
    private static Component message(String key, Object... args) {
        Minecraft.getInstance().options.save();
        return Component.translatable(key, args);
    }

    /**
     * @return the current chat.
     */
    @Unique
    private static ChatComponent getChatHud() {
        return Minecraft.getInstance().gui.getChat();
    }

    /**
     * Initializes all Simple Keybinds.
     */
    public static void initKeybinds() {
    }
}