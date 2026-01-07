package net.dillon.simplekeybinds.mixin;

import net.caffeinemc.mods.sodium.client.gui.VideoSettingsScreen;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(VideoSettingsScreen.class)
public class SliderControlMixin {

    /**
     * Fixes new sodium bug where brightness resets after closing and saving options in the Sodium menu.
     */
    @Inject(method = "close", at = @At("TAIL"))
    private void saveNewBrightness(CallbackInfo ci) {
        MinecraftClient.getInstance().options.getGamma().setValue(SimpleKeybinds.cachedBrightness);
        MinecraftClient.getInstance().options.write();
    }
}