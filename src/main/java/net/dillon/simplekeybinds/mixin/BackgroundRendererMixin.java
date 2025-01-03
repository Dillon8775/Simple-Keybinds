package net.dillon.simplekeybinds.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon.simplekeybinds.core.SimpleKeybindsCore;
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

	/**
	 * Removes fog from the game when using the keybind.
	 * <p>This mixin is not applied if the {@code speedrunner mod} is loaded. See {@link ConditionalMixinPlugin} for more.</p>
	 */
	@Overwrite
	public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
		FluidState fluidState = camera.getSubmergedFluidState();
		Entity entity = camera.getFocusedEntity();
		float f;
		if (fluidState.isIn(FluidTags.WATER)) {
			f = 1.0F;
			f = 0.05F;
			if (entity instanceof ClientPlayerEntity) {
				ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
				f -= clientPlayerEntity.getUnderwaterVisibility() * clientPlayerEntity.getUnderwaterVisibility() * 0.03F;
				Biome biome = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
				if (biome.getCategory() == Biome.Category.SWAMP) {
					f += 0.005F;
				}
			}

			RenderSystem.fogDensity(f);
			RenderSystem.fogMode(GlStateManager.FogMode.EXP2);
		} else {
			float g;
			final int fog = 2147483647;
			if (fluidState.isIn(FluidTags.LAVA)) {
				if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
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

			RenderSystem.fogStart(f);
			RenderSystem.fogEnd(g);
			RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
			RenderSystem.setupNvFogDistance();
		}
	}
}