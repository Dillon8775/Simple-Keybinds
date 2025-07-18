package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(FogRenderer.class)
public class FogRendererMixin {

	/**
	 * Removes fog from the game when using the keybind.
	 * <p>This mixin is not applied if the {@code speedrunner mod} is loaded. See {@link ConditionalMixinPlugin} for more.</p>
	 */
	@Inject(method = "applyFog(Lnet/minecraft/client/render/Camera;IZLnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void modifyFogEnd(Camera camera, int viewDistance, boolean thick, RenderTickCounter tickCounter, float skyDarkness, ClientWorld world, CallbackInfoReturnable<Vector4f> cir, float f, Vector4f vector4f, float g, CameraSubmersionType cameraSubmersionType, Entity entity, FogData fogData, float h) {
		if (!SimpleKeybinds.fog &&
				cameraSubmersionType != CameraSubmersionType.WATER &&
				cameraSubmersionType != CameraSubmersionType.LAVA &&
				cameraSubmersionType != CameraSubmersionType.POWDER_SNOW) {
			fogData.renderDistanceEnd = Integer.MAX_VALUE;
			fogData.environmentalEnd = Integer.MAX_VALUE;
		}
	}
}