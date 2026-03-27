package net.dillon.simplekeybinds.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.util.ModUtil.options;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    /**
     * Implements the auto-brightness functionality.
     */
    @Inject(method = "extractLevel", at = @At("TAIL"))
    private void autoBrightness(DeltaTracker deltaTracker, Camera camera, float deltaPartialTick, CallbackInfo ci) {
        if (!options().autoBrightness || Minecraft.getInstance().level == null) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        Entity entity = minecraft.getCameraEntity();
        if (entity == null) {
            return;
        }

        BlockPos feetPos = entity.blockPosition();
        int rawBrightness = minecraft.level.getChunkSource().getLightEngine().getRawBrightness(feetPos, 0);
        Minecraft.getInstance().options.gamma().set(5.0 - (rawBrightness / 15.0D) * 4.0D);
        Minecraft.getInstance().options.save();
    }
}