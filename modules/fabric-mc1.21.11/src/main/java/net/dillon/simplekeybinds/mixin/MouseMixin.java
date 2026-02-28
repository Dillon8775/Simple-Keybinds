package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.SimpleKeybinds.muted;

/**
 * Handles {@code Simple Keybind} functions for increase/decrease keybinds.
 */
@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void handleScrollableKeybinds(long window, double horizontal, double vertical, CallbackInfo ci) {
        MuteCallback.tickScroll();
        if (this.client != null && vertical != 0) {
            float min;
            float max;
            float delta;
            if (ModKeybinds.CHANGE_BRIGHTNESS.isPressed()) {
                min = (float) SimpleKeybinds.minBrightness;
                max = (float) SimpleKeybinds.maxBrightness;
                float gamma = this.client.options.getGamma().getValue().floatValue();
                delta = 0.5F * (vertical > 0 ? 1 : -1);
                gamma += delta;
                gamma = Math.max(min, Math.min(max, gamma));
                this.client.options.getGamma().setValue((double) gamma);
                SimpleKeybinds.cachedBrightness = this.client.options.getGamma().getValue();
                this.sendMessage(Text.translatable("simplekeybinds.changed_brightness", (int) (gamma * 100)).append("%").formatted(Formatting.GREEN));
                ci.cancel();
            } else if (ModKeybinds.CHANGE_GUI_SCALE.isPressed()) {
                min = 1;
                max = !this.client.isRunning() ? 2147483646 : this.client.getWindow().calculateScaleFactor(0, this.client.forcesUnicodeFont());
                int scale = this.client.options.getGuiScale().getValue();
                delta = (vertical > 0 ? 1 : -1);
                scale += delta;
                scale = Math.max((int) min, Math.min((int) max, scale));
                this.client.options.getGuiScale().setValue(scale);
                this.sendMessage(Text.translatable("simplekeybinds.changed_gui_scale", scale));
                ci.cancel();
            } else if (ModKeybinds.CHANGE_FOV.isPressed()) {
                min = 30;
                max = 110;
                int fov = this.client.options.getFov().getValue();
                delta = (vertical > 0 ? -2 : 2);
                fov += delta;
                fov = Math.max((int) min, Math.min((int) max, fov));
                this.client.options.getFov().setValue(fov);
                this.sendMessage(Text.translatable("simplekeybinds.changed_fov", fov));
                ci.cancel();
            } else if (ModKeybinds.CHANGE_MASTER_VOLUME.isPressed()) {
                if (muted) {
                    this.sendMessage(Text.translatable("simplekeybinds.cant_change_volume"));
                    return;
                }
                min = 0;
                max = 1;
                double volume = this.client.options.getSoundVolumeOption(SoundCategory.MASTER).getValue();
                delta = (vertical > 0 ? 3 : -3) / (float) 100;
                volume += delta;
                volume = Math.max(min, Math.min((int) max, volume));
                this.client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(volume);
                this.sendMessage(Text.translatable("simplekeybinds.changed_master_volume", (int) (volume * 100)).append(Text.literal("%")));
                ci.cancel();
            } else if (ModKeybinds.CHANGE_RENDER_DISTANCE.isPressed()) {
                min = 2;
                max = 32;
                int renderDistance = this.client.options.getViewDistance().getValue();
                delta = (vertical > 0 ? 1 : -1);
                renderDistance += delta;
                renderDistance = Math.max((int) min, Math.min((int) max, renderDistance));
                this.client.options.getViewDistance().setValue(renderDistance);
                this.sendMessage(Text.translatable("simplekeybinds.changed_render_distance", renderDistance).formatted(renderDistance < 9 ? Formatting.GREEN :
                        renderDistance < 16 ? Formatting.AQUA :
                                renderDistance < 24 ? Formatting.GOLD :
                                        renderDistance > 29 ? Formatting.DARK_RED :
                                                Formatting.RED));
                ci.cancel();
            } else if (ModKeybinds.CHANGE_ENTITY_DISTANCE.isPressed()) {
                min = 0.5F;
                max = 5.0F;
                float entityDistance = this.client.options.getEntityDistanceScaling().getValue().floatValue();
                delta = 0.25F * (vertical > 0 ? 1 : -1);
                if (entityDistance == max && vertical > 0) {
                    entityDistance = min;
                } else if (entityDistance == min && vertical < 0) {
                    entityDistance = max;
                } else {
                    entityDistance += delta;
                }
                entityDistance = Math.max(min, Math.min(max, entityDistance));
                this.client.options.getEntityDistanceScaling().setValue((double) entityDistance);
                this.sendMessage(Text.translatable("simplekeybinds.changed_entity_distance", (int) (entityDistance * 100)).append("%").formatted(Formatting.GOLD));
                ci.cancel();
            }
        }
    }

    /**
     * Sends the message for the updated setting, and writes it to the "options.txt" file.
     */
    @Unique
    private void sendMessage(Text message) {
        MinecraftClient.getInstance().options.write();
        this.client.player.sendMessage(message, true);
    }
}