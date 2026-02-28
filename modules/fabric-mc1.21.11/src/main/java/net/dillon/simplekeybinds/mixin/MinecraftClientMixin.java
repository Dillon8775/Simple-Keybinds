package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.SimpleKeybinds.*;

/**
 * Handles {@code Simple Keybind} functions.
 */
@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final
    public InGameHud inGameHud;
    @Shadow
    @Nullable
    public ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickScrolling(CallbackInfo ci) {
        MuteCallback.unscroll();
        MuteCallback.allowMuting();
    }

    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        while (ModKeybinds.CLEAR_CHAT.wasPressed()) {
            if (this.inGameHud != null) {
                this.getChatHud().clear(false);
            }
        }

        while (ModKeybinds.PAUSE_WITHOUT_MENU.wasPressed()) {
            MinecraftClient.getInstance().setScreen(new GameMenuScreen(false));
        }

        while (ModKeybinds.RELOAD_CHUNKS.wasPressed()) {
            MinecraftClient.getInstance().worldRenderer.reload();
            this.getChatHud().addMessage(message("debug.reload_chunks.message"));
        }

        while (ModKeybinds.SHOW_ADVANCED_TOOLTIPS.wasPressed()) {
            MinecraftClient.getInstance().options.advancedItemTooltips = !MinecraftClient.getInstance().options.advancedItemTooltips;
            this.getChatHud().addMessage(message(MinecraftClient.getInstance().options.advancedItemTooltips ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off"));
        }

        while (ModKeybinds.TOGGLE_CHUNK_BORDERS.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugHudEntryList.toggleVisibility(DebugHudEntries.CHUNK_BORDERS);
            this.getChatHud().addMessage(message(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off"));
        }

        while (ModKeybinds.TOGGLE_HITBOXES.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugHudEntryList.toggleVisibility(DebugHudEntries.ENTITY_HITBOXES);
            this.getChatHud().addMessage(message(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off"));
        }

        while (ModKeybinds.TOGGLE_FULLBRIGHT.wasPressed()) {
            double currentBrightness = MinecraftClient.getInstance().options.getGamma().getValue();
            if (!SimpleKeybinds.fullBright) {
                SimpleKeybinds.previousBrightness = currentBrightness;
                if (currentBrightness >= 8.0D) {
                    SimpleKeybinds.previousBrightness = 1.0D;
                }
            }
            SimpleKeybinds.fullBright = !SimpleKeybinds.fullBright;
            MinecraftClient.getInstance().options.getGamma().setValue(SimpleKeybinds.fullBright ? SimpleKeybinds.maxBrightness : SimpleKeybinds.previousBrightness);
            this.getChatHud().addMessage(message(SimpleKeybinds.fullBright ? "simplekeybinds.fullbright.on" : "simplekeybinds.fullbright.off"));
        }

        // If the Speedrunner Mod is loaded, the fog keybinding won't work.
        // You will have to use the Speedrunner Mod fog keybind.
        while (ModKeybinds.TOGGLE_FOG.wasPressed()) {
            if (!SimpleKeybinds.isSpeedrunnerModLoaded()) {
                SimpleKeybinds.fog = !SimpleKeybinds.fog;
                MinecraftClient.getInstance().worldRenderer.reload();
                this.getChatHud().addMessage(message(SimpleKeybinds.fog ? "simplekeybinds.fog.on" : "simplekeybinds.fog.off"));
            } else {
                this.getChatHud().addMessage(message("simplekeybinds.speedrunner_mod_loaded_keybindings"));
            }
        }

        if (MinecraftClient.getInstance().isCtrlPressed() && ModKeybinds.MUTE_GAME.isPressed() && MuteCallback.muteCooldown == 0) {
            MuteCallback.resetMuteCooldown();
            GameOptions options = MinecraftClient.getInstance().options;
            if (!muted) {
                this.mute(options);
            } else {
                this.unmute(options);
            }
            if (!scrolling && MinecraftClient.getInstance().player != null) {
                this.player.sendMessage(muted
                        ? Text.translatable("simplekeybinds.muted").formatted(Formatting.RED)
                        : Text.translatable("simplekeybinds.unmuted").formatted(Formatting.GREEN), true);
            }
            MinecraftClient.getInstance().options.write();
        }
    }

    /**
     * Mutes the game.
     */
    @Unique
    private void mute(GameOptions options) {
        if (scrolling) {
            return;
        }

        double currentVolume = options.getSoundVolumeOption(SoundCategory.MASTER).getValue();
        if (currentVolume == 0.0F) {
            this.unmute(options);
            return;
        }

        cachedVolume = currentVolume;
        muted = true;

        options.getSoundVolumeOption(SoundCategory.MASTER).setValue(0D);
    }

    /**
     * Unmutes the game.
     */
    @Unique
    private void unmute(GameOptions options) {
        options.getSoundVolumeOption(SoundCategory.MASTER).setValue((cachedVolume == 0.0D ? 0.5D : cachedVolume));
        muted = false;
    }

    /**
     * Sends the message for the updated setting, and writes it to the "options.txt" file.
     */
    @Unique
    private Text message(String key, Object... args) {
        MinecraftClient.getInstance().options.write();
        return Text.translatable(key, args);
    }

    /**
     * @return the current chat.
     */
    @Unique
    private ChatHud getChatHud() {
        return this.inGameHud.getChatHud();
    }
}