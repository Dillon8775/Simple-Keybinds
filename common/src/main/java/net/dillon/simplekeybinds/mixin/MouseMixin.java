package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.callback.MuteCallback;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.helper.ModHelper.muted;
import static net.dillon.simplekeybinds.helper.ModHelper.sendClientMessage;

/**
 * Handles {@code Simple Keybind} functions for increase/decrease keybinds.
 */
@Mixin(MouseHandler.class)
public class MouseMixin {
    @Shadow @Final
    private Minecraft minecraft;

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void handleScrollableKeybinds(long window, double horizontal, double vertical, CallbackInfo ci) {
        MuteCallback.tickScroll();
        if (this.minecraft != null && vertical != 0) {
            float min;
            float max;
            float delta;
            if (ModKeyMappings.CHANGE_BRIGHTNESS.isActiveAndDown()) {
                if (ModKeyMappings.autoBrightness()) {
                    return;
                }
                min = (float) ModHelper.minBrightness;
                max = (float) ModHelper.maxBrightness;
                float gamma = this.minecraft.options.gamma().get().floatValue();
                delta = 0.5F * (vertical > 0 ? 1 : -1);
                gamma += delta;
                gamma = Math.max(min, Math.min(max, gamma));
                this.minecraft.options.gamma().set((double)gamma);
                this.sendMessage(Component.translatable("simplekeybinds.changed_brightness", (int)(gamma * 100)).append("%").withStyle(ChatFormatting.GREEN));
                ci.cancel();
            } else if (ModKeyMappings.CHANGE_GUI_SCALE.isActiveAndDown()) {
                min = 1;
                max = !this.minecraft.isRunning() ? 2147483646 : this.minecraft.getWindow().calculateScale(0, this.minecraft.isEnforceUnicode());
                int scale = this.minecraft.options.guiScale().get();
                delta = (vertical > 0 ? 1 : -1);
                scale += delta;
                scale = Math.max((int)min, Math.min((int)max, scale));
                this.minecraft.options.guiScale().set(scale);
                this.sendMessage(Component.translatable("simplekeybinds.changed_gui_scale", scale));
                ci.cancel();
            } else if (ModKeyMappings.CHANGE_FOV.isActiveAndDown()) {
                min = 30;
                max = 110;
                int fov = this.minecraft.options.fov().get();
                delta = (vertical > 0 ? -2 : 2);
                fov += delta;
                fov = Math.max((int)min, Math.min((int)max, fov));
                this.minecraft.options.fov().set(fov);
                this.sendMessage(Component.translatable("simplekeybinds.changed_fov", fov));
                ci.cancel();
            } else if (ModKeyMappings.CHANGE_MASTER_VOLUME.isActiveAndDown()) {
                if (muted) {
                    this.sendMessage(Component.translatable("simplekeybinds.cant_change_volume"));
                    return;
                }
                min = 0;
                max = 1;
                double volume = this.minecraft.options.getSoundSourceOptionInstance(SoundSource.MASTER).get();
                delta = (vertical > 0 ? 3 : -3) / (float)100;
                volume += delta;
                volume = Math.max(min, Math.min((int)max, volume));
                this.minecraft.options.getSoundSourceOptionInstance(SoundSource.MASTER).set(volume);
                this.sendMessage(Component.translatable("simplekeybinds.changed_master_volume", (int)(volume * 100)).append(Component.literal("%")));
                ci.cancel();
            } else if (ModKeyMappings.CHANGE_RENDER_DISTANCE.isActiveAndDown()) {
                min = 2;
                max = 32;
                int renderDistance = this.minecraft.options.renderDistance().get();
                delta = (vertical > 0 ? 1 : -1);
                renderDistance += delta;
                renderDistance = Math.max((int)min, Math.min((int)max, renderDistance));
                this.minecraft.options.renderDistance().set(renderDistance);
                this.sendMessage(Component.translatable("simplekeybinds.changed_render_distance", renderDistance).withStyle(renderDistance < 9 ? ChatFormatting.GREEN :
                        renderDistance < 16 ? ChatFormatting.AQUA :
                                renderDistance < 24 ? ChatFormatting.GOLD :
                                        renderDistance > 29 ? ChatFormatting.DARK_RED :
                                                ChatFormatting.RED));
                ci.cancel();
            } else if (ModKeyMappings.CHANGE_ENTITY_DISTANCE.isActiveAndDown()) {
                min = 0.5F;
                max = 5.0F;
                float entityDistance = this.minecraft.options.entityDistanceScaling().get().floatValue();
                delta = 0.25F * (vertical > 0 ? 1 : -1);
                if (entityDistance == max && vertical > 0) {
                    entityDistance = min;
                } else if (entityDistance == min && vertical < 0) {
                    entityDistance = max;
                } else {
                    entityDistance += delta;
                }
                entityDistance = Math.max(min, Math.min(max, entityDistance));
                this.minecraft.options.entityDistanceScaling().set((double)entityDistance);
                this.sendMessage(Component.translatable("simplekeybinds.changed_entity_distance", (int)(entityDistance * 100)).append("%").withStyle(ChatFormatting.GOLD));
                ci.cancel();
            }
        }
    }

    /**
     * Sends the message for the updated setting, and writes it to the "options.txt" file.
     */
    @Unique
    private void sendMessage(Component message) {
        Minecraft.getInstance().options.save();
        sendClientMessage(this.minecraft.player, message);
    }
}