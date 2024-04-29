package net.dillon.simplekeybinds.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class	)
public class BackgroundRendererMixin {

	@Overwrite
	public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
		FluidState fluidState = camera.getSubmergedFluidState();
		Entity entity = camera.getFocusedEntity();
		float s;
		if (fluidState.isIn(FluidTags.WATER)) {
			s = 1.0F;
			s = 0.05F;
			if (entity instanceof ClientPlayerEntity) {
				ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
				s -= clientPlayerEntity.getUnderwaterVisibility() * clientPlayerEntity.getUnderwaterVisibility() * 0.03F;
				Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
				if (biome.getCategory() == Biome.Category.SWAMP) {
					s += 0.005F;
				}
			}

			RenderSystem.fogDensity(s);
			RenderSystem.fogMode(GlStateManager.FogMode.EXP2);
		} else {
			float v;
			final int fog = 2147483647;
			final float lavaVisionDistance = 35.0F;
			if (fluidState.isIn(FluidTags.LAVA)) {
				if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
					s = 0.0F;
					v = lavaVisionDistance;
				} else {
					s = 0.25F;
					v = 1.0F;
				}
			} else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
				int k = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
				float l = MathHelper.lerp(Math.min(1.0F, (float)k / 20.0F), viewDistance, 5.0F);
				if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
					s = 0.0F;
					v = l * 0.8F;
				} else {
					s = l * 0.25F;
					v = l;
				}
			} else if (thickFog) {
				s = viewDistance * 0.05F;
				if (!SimpleKeybinds.options().fog) {
					v = fog;
				} else {
					v = Math.min(viewDistance, 192.0F) * 0.5F;
				}
			} else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
				s = 0.0F;
				v = viewDistance;
			} else {
				s = viewDistance * 0.75F;
				if (!SimpleKeybinds.options().fog) {
					v = fog;
				} else {
					v = viewDistance;
				}
			}

			RenderSystem.fogStart(s);
			RenderSystem.fogEnd(v);
			RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
			RenderSystem.setupNvFogDistance();
		}
	}
}