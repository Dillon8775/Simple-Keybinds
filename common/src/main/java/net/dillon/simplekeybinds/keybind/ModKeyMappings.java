package net.dillon.simplekeybinds.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.*;
import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.option.ModClientOptions;
import net.dillon.simplekeybinds.platform.ModReferences;
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
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.simplekeybinds.helper.ModHelper.*;
import static net.dillon.simplekeybinds.option.OptionInstances.client;

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
                Minecraft.getInstance().gui.setScreen(new PauseScreen(false));
                return true;
            })
            .build();

    private static final ManagedKeyMapping RELOAD_CHUNKS = Kuma.createKeyMapping(ofSimpleKeybinds("reload_chunks"))
            .overrideCategory(DEFAULT_CATEGORY)
            .handleWorldInput(handler -> {
                Minecraft.getInstance().levelExtractor.allChanged();
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
                if (ModReferences.isModLoaded(ModReferences.QUALITY_OF_QUESO)) {
                    getChatHud().addClientSystemMessage(message("simplekeybinds.options.fog.disabled"));
                    return false;
                }
                ModClientOptions.INSTANCE.update(options -> options.fog = !options.fog);
                Minecraft.getInstance().levelExtractor.allChanged();
                getChatHud().addClientSystemMessage(message(client().fog ? "simplekeybinds.fog.on" : "simplekeybinds.fog.off"));
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

    public static final ManagedKeyMapping CHANGE_MASTER_VOLUME = Kuma.createKeyMapping(ofSimpleKeybinds("change_master_volume"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_M))
            .build();

    public static final ManagedKeyMapping CHANGE_RENDER_DISTANCE = Kuma.createKeyMapping(ofSimpleKeybinds("change_render_distance"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_U))
            .build();

    public static final ManagedKeyMapping CHANGE_ENTITY_DISTANCE = Kuma.createKeyMapping(ofSimpleKeybinds("change_entity_distance"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_H))
            .build();

    public static final ManagedKeyMapping CHANGE_BRIGHTNESS = Kuma.createKeyMapping(ofSimpleKeybinds("change_brightness"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_J))
            .build();

    public static final ManagedKeyMapping CHANGE_FOV = Kuma.createKeyMapping(ofSimpleKeybinds("change_fov"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_R))
            .build();

    public static final ManagedKeyMapping CHANGE_GUI_SCALE = Kuma.createKeyMapping(ofSimpleKeybinds("change_gui_scale"))
            .overrideCategory(SCROLLING_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_G))
            .build();

    /**
     * @return if auto brightness is enabled, and sends a message to the player.
     */
    public static boolean autoBrightness() {
        if (client().autoBrightness) {
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
        return Minecraft.getInstance().gui.hud.getChat();
    }

    /**
     * Initializes all Simple Keybinds.
     */
    public static void initKeybinds() {
    }
}