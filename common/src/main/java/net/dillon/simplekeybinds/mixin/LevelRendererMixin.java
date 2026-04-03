package net.dillon.simplekeybinds.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.joml.Matrix4f;
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
    @Inject(method = "renderLevel", at = @At("TAIL"))
    private void autoBrightness(DeltaTracker p_348530_, boolean p_109603_, Camera p_109604_, GameRenderer p_109605_, LightTexture p_109606_, Matrix4f p_254120_, Matrix4f p_323920_, CallbackInfo ci) {
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