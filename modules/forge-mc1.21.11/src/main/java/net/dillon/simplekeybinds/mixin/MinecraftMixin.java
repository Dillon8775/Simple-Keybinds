package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Final
    public Gui gui;

    @Inject(method = "handleKeybinds", at = @At("TAIL"))
    private void handleKeyPressing(CallbackInfo ci) {
        while (ModKeybinds.CLEAR_CHAT.consumeClick()) {
            if (this.gui != null) {
                this.getChatHud().clearMessages(false);
            }
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
            boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.CHUNK_BORDERS);
            this.getChatHud().addMessage(message(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off"));
        }

        while (ModKeybinds.TOGGLE_HITBOXES.consumeClick()) {
            boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
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
        return this.gui.getChat();
    }
}