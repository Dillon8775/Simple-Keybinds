package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Handles {@code Simple Keybind} functions for increase/decrease keybinds.
 */
@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void handleIncreaseDecreaseKeybinds(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (this.client != null && vertical != 0) {
            float min;
            float max;
            float delta;
            if (ModKeybinds.CHANGE_BRIGHTNESS.isPressed()) {
                min = (float)SimpleKeybinds.minBrightness;
                max = (float)SimpleKeybinds.maxBrightness;
                float gamma = this.client.options.getGamma().getValue().floatValue();
                delta = 0.5F * (vertical > 0 ? 1 : -1);
                gamma += delta;
                if (gamma < min) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_brightness.low_error").formatted(Formatting.GREEN), true);
                } else if (gamma > max) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_brightness.high_error").formatted(Formatting.GREEN), true);
                } else {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_brightness", (int)(gamma * 100)).append("%").formatted(Formatting.GREEN), true);
                }
                gamma = Math.max(min, Math.min(max, gamma));
                this.client.options.getGamma().setValue((double)gamma);
                ci.cancel();
            } else if (ModKeybinds.CHANGE_RENDER_DISTANCE.isPressed()) {
                min = 2;
                max = 32;
                int renderDistance = this.client.options.getViewDistance().getValue();
                delta = (vertical > 0 ? 1 : -1);
                renderDistance += delta;
                if (renderDistance < min) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_render_distance.low_error").formatted(Formatting.AQUA), true);
                } else if (renderDistance > max) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_render_distance.high_error").formatted(Formatting.AQUA), true);
                } else {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_render_distance", renderDistance).formatted(Formatting.AQUA), true);
                }
                renderDistance = Math.max((int)min, Math.min((int)max, renderDistance));
                this.client.options.getViewDistance().setValue(renderDistance);
                ci.cancel();
            } else if (ModKeybinds.CHANGE_ENTITY_DISTANCE.isPressed()) {
                min = 0.5F;
                max = 5.0F;
                float entityDistance = this.client.options.getEntityDistanceScaling().getValue().floatValue();
                delta = 0.25F * (vertical > 0 ? 1 : -1);
                entityDistance += delta;
                if (entityDistance < min) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_entity_distance.low_error").formatted(Formatting.GOLD), true);
                } else if (entityDistance > max) {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_entity_distance.high_error").formatted(Formatting.GOLD), true);
                } else {
                    this.client.player.sendMessage(Text.translatable("simplekeybinds.changed_entity_distance", (int)(entityDistance * 100)).append("%").formatted(Formatting.GOLD), true);
                }
                entityDistance = Math.max(min, Math.min(max, entityDistance));
                this.client.options.getEntityDistanceScaling().setValue((double)entityDistance);
                ci.cancel();
            }
        }
    }
}