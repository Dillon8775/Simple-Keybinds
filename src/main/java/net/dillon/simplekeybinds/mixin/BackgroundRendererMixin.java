package net.dillon.simplekeybinds.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.simplekeybinds.core.SimpleKeybindsCore;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class	)
public class BackgroundRendererMixin {

	/**
	 * Removes fog from the game when using the keybind.
	 * <p>This mixin is not applied if the {@code speedrunner mod} is loaded. See {@link ConditionalMixinPlugin} for more.</p>
	 */
	@Overwrite
	public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
		CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
		Entity entity = camera.getFocusedEntity();
		float f;
		if (cameraSubmersionType == CameraSubmersionType.WATER) {
			f = 192.0F;
			if (entity instanceof ClientPlayerEntity) {
				ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
				f *= Math.max(0.25F, clientPlayerEntity.getUnderwaterVisibility());
				Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
				if (biome.getCategory() == Biome.Category.SWAMP) {
					f *= 0.85F;
				}
			}

			RenderSystem.setShaderFogStart(-8.0F);
			RenderSystem.setShaderFogEnd(f * 0.5F);
		} else {
			float g;
			final int fog = 2147483647;
			if (cameraSubmersionType == CameraSubmersionType.LAVA) {
				if (entity.isSpectator()) {
					f = -8.0F;
					g = viewDistance * 0.5F;
				} else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
					f = 0.0F;
					g = 3.0F;
				} else {
					f = 0.25F;
					g = 1.0F;
				}
			} else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
				int i = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
				float h = MathHelper.lerp(Math.min(1.0F, (float)i / 20.0F), viewDistance, 5.0F);
				if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
					f = 0.0F;
					g = h * 0.8F;
				} else {
					f = h * 0.25F;
					g = h;
				}
			} else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
				if (entity.isSpectator()) {
					f = -8.0F;
					g = viewDistance * 0.5F;
				} else {
					f = 0.0F;
					g = 2.0F;
				}
			} else if (thickFog) {
				f = viewDistance * 0.05F;
				if (!SimpleKeybindsCore.fog) {
					g = fog;
				} else {
					g = Math.min(viewDistance, 192.0F) * 0.5F;
				}
			} else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
				f = 0.0F;
				g = viewDistance;
			} else {
				f = viewDistance * 0.75F;
				if (!SimpleKeybindsCore.fog) {
					g = fog;
				} else {
					g = viewDistance;
				}
			}

			RenderSystem.setShaderFogStart(f);
			RenderSystem.setShaderFogEnd(g);
		}
	}
}