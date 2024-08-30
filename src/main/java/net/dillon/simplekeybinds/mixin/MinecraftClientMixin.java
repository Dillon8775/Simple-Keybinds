package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.keybinds.ModKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.SimpleKeybinds.isSpeedrunnerModLoaded;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final
    public InGameHud inGameHud;

    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        while (ModKeybinds.CLEAR_CHAT.wasPressed()) {
            if (this.inGameHud != null) {
                this.inGameHud.getChatHud().clear(false);
            }
        }

        while (ModKeybinds.DECREASE_BRIGHTNESS.wasPressed()) {
            if (MinecraftClient.getInstance().options.getGamma().getValue() > SimpleKeybinds.minBrightness) {
                MinecraftClient.getInstance().options.getGamma().setValue(MinecraftClient.getInstance().options.getGamma().getValue() - 0.5D);
            } else {
                message("simplekeybinds.brightness_error");
            }
        }

        while (ModKeybinds.INCREASE_BRIGHTNESS.wasPressed()) {
            if (MinecraftClient.getInstance().options.getGamma().getValue() < SimpleKeybinds.maxBrightness) {
                MinecraftClient.getInstance().options.getGamma().setValue(MinecraftClient.getInstance().options.getGamma().getValue() + 0.5D);
            } else {
                message("simplekeybinds.brightness_error");
            }
        }

        while (ModKeybinds.PAUSE_WITHOUT_MENU.wasPressed()) {
            MinecraftClient.getInstance().setScreen(new GameMenuScreen(false));
        }

        while (ModKeybinds.RELOAD_CHUNKS.wasPressed()) {
            MinecraftClient.getInstance().worldRenderer.reload();
            message("debug.reload_chunks.message");
        }

        while (ModKeybinds.SHOW_ADVANCED_TOOLTIPS.wasPressed()) {
            MinecraftClient.getInstance().options.advancedItemTooltips = !MinecraftClient.getInstance().options.advancedItemTooltips;
            MinecraftClient.getInstance().options.write();
            message(MinecraftClient.getInstance().options.advancedItemTooltips ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off");
        }

        while (ModKeybinds.TOGGLE_CHUNK_BORDERS.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
            message(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
        }

        while (ModKeybinds.TOGGLE_HITBOXES.wasPressed()) {
            boolean bl = !MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes();
            MinecraftClient.getInstance().getEntityRenderDispatcher().setRenderHitboxes(bl);
            message(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
        }

        // If the Speedrunner Mod is loaded, the fog keybinding won't work.
        // You will have to use the Speedrunner Mod fog keybind.
        while (ModKeybinds.TOGGLE_FOG.wasPressed()) {
            if (!SimpleKeybinds.isSpeedrunnerModLoaded()) {
                SimpleKeybinds.fog = !SimpleKeybinds.fog;
                MinecraftClient.getInstance().worldRenderer.reload();
                message(SimpleKeybinds.fog ? "simplekeybinds.fog.on" : "simplekeybinds.fog.off");
            } else {
                message("simplekeybinds.speedrunner_mod_loaded_keybindings");
            }
        }

        // If the Speedrunner Mod is loaded, the fullbright keybinding won't work.
        // You will have to use the Speedrunner Mod fullbright keybind.
        while (ModKeybinds.TOGGLE_FULLBRIGHT.wasPressed()) {
            if (!SimpleKeybinds.isSpeedrunnerModLoaded()) {
                SimpleKeybinds.fullBright = !SimpleKeybinds.fullBright;
                MinecraftClient.getInstance().options.getGamma().setValue(SimpleKeybinds.fullBright ? SimpleKeybinds.maxBrightness : 1.0D);
                message(SimpleKeybinds.fullBright ? "simplekeybinds.fullbright.on" : "simplekeybinds.fullbright.off");
            } else {
                message("simplekeybinds.speedrunner_mod_loaded_keybindings");
            }
        }
    }

    @Unique
    private void message(String key, Object... args) {
        this.inGameHud.getChatHud().addMessage((Text.literal("")).append((Text.translatable("debug.prefix")).formatted(Formatting.YELLOW, Formatting.BOLD)).append(" ").append(Text.translatable(key, args)));
    }
}