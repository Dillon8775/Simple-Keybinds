package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import static net.dillon.simplekeybinds.SimpleKeybinds.*;

@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickScrolling(CallbackInfo ci) {
        MuteCallback.unscroll();
        MuteCallback.allowMuting();
    }

    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    private void handleKeyPressing(CallbackInfo ci) {
        while (ModKeybinds.CLEAR_CHAT.consumeClick()) {
            this.getChatHud().clearMessages(false);
        }

        while (ModKeybinds.PAUSE_WITHOUT_MENU.consumeClick()) {
            Minecraft.getInstance().setScreen(new PauseScreen(false));
        }

        while (ModKeybinds.RELOAD_CHUNKS.consumeClick()) {
            Minecraft.getInstance().levelRenderer.allChanged();
            this.getChatHud().addMessage(message("debug.reload_chunks.message"));
        }

        while (ModKeybinds.SHOW_ADVANCED_TOOLTIPS.consumeClick()) {
            Minecraft.getInstance().options.advancedItemTooltips = !Minecraft.getInstance().options.advancedItemTooltips;
            this.getChatHud().addMessage(message(Minecraft.getInstance().options.advancedItemTooltips ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off"));
        }

        while (ModKeybinds.TOGGLE_CHUNK_BORDERS.consumeClick()) {
            boolean bl = Minecraft.getInstance().debugRenderer.switchRenderChunkborder();
            this.getChatHud().addMessage(message(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off"));
        }

        while (ModKeybinds.TOGGLE_HITBOXES.consumeClick()) {
            boolean bl = Minecraft.getInstance().getEntityRenderDispatcher().shouldRenderHitBoxes();
            this.getChatHud().addMessage(message(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off"));
        }

        // If the Speedrunner Mod is loaded, the fog keybinding won't work.
        // You will have to use the Speedrunner Mod fog keybind.
        while (ModKeybinds.TOGGLE_FOG.consumeClick()) {
            SimpleKeybinds.fog = !SimpleKeybinds.fog;
            Minecraft.getInstance().levelRenderer.allChanged();
            this.getChatHud().addMessage(message(SimpleKeybinds.fog ? "simplekeybinds.fog.on" : "simplekeybinds.fog.off"));
        }

        // If the Speedrunner Mod is loaded, the fullbright keybinding won't work.
        // You will have to use the Speedrunner Mod fullbright keybind.
        while (ModKeybinds.TOGGLE_FULLBRIGHT.consumeClick()) {
            double currentBrightness = Minecraft.getInstance().options.gamma().get();
            if (!SimpleKeybinds.fullBright) {
                SimpleKeybinds.previousBrightness = currentBrightness;
                if (currentBrightness >= 8.0D) {
                    SimpleKeybinds.previousBrightness = 1.0D;
                }
            }
            SimpleKeybinds.fullBright = !SimpleKeybinds.fullBright;
            Minecraft.getInstance().options.gamma().set(SimpleKeybinds.fullBright ? SimpleKeybinds.maxBrightness : SimpleKeybinds.previousBrightness);
            this.getChatHud().addMessage(message(SimpleKeybinds.fullBright ? "simplekeybinds.fullbright.on" : "simplekeybinds.fullbright.off"));
        }

        if (Screen.hasControlDown() && ModKeybinds.MUTE_GAME.isDown() && MuteCallback.muteCooldown == 0) {
            MuteCallback.resetMuteCooldown();
            Options options = Minecraft.getInstance().options;
            if (!muted) {
                this.mute(options);
            } else {
                this.unmute(options);
            }
            if (!scrolling && Minecraft.getInstance().player != null) {
                this.player.displayClientMessage(muted
                        ? Component.translatable("simplekeybinds.muted").withStyle(ChatFormatting.RED)
                        : Component.translatable("simplekeybinds.unmuted").withStyle(ChatFormatting.GREEN), true);
            }
            Minecraft.getInstance().options.save();
        }
    }

    /**
     * Mutes the game.
     */
    @Unique
    private void mute(Options options) {
        if (scrolling) {
            return;
        }

        double currentVolume = options.getSoundSourceOptionInstance(SoundSource.MASTER).get();
        if (currentVolume == 0.0F) {
            unmute(options);
            return;
        }

        cachedVolume = currentVolume;
        muted = true;

        options.getSoundSourceOptionInstance(SoundSource.MASTER).set(0D);
    }

    /**
     * Unmutes the game.
     */
    @Unique
    private void unmute(Options options) {
        options.getSoundSourceOptionInstance(SoundSource.MASTER).set((cachedVolume == 0.0D ? 0.5D : cachedVolume));
        muted = false;
    }

    /**
     * Sends the message for the updated setting, and writes it to the "options.txt" file.
     */
    @Unique
    private Component message(String key, Object... args) {
        Minecraft.getInstance().options.save();
        return Component.translatable(key, args);
    }

    /**
     * @return the current chat.
     */
    @Unique
    private ChatComponent getChatHud() {
        return Minecraft.getInstance().gui.getChat();
    }
}