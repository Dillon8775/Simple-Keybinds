package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.callback.MuteCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.simplekeybinds.option.OptionInstances.client;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Ticks muting callbacks.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void tickScrolling(CallbackInfo ci) {
        MuteCallback.unscroll();
        MuteCallback.allowMuting();

        if (!client().autoBrightness || Minecraft.getInstance().level == null) {
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