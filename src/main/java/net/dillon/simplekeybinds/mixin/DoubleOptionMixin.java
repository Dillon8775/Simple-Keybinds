package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
@Mixin(DoubleOption.class)
public class DoubleOptionMixin {
    @Shadow @Final @Mutable
    private BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;
    @Shadow @Final @Mutable
    protected double min;
    @Shadow @Final @Mutable
    protected float step;
    @Shadow @Mutable
    protected double max;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, CallbackInfo ci) {
        if (key.equals("options.gamma")) {
            this.min = SimpleKeybinds.minBrightness;
            this.max = SimpleKeybinds.maxBrightness;
            this.step = 0.1F;
            this.displayStringGetter = this::displayStringGetter;
        }
    }

    @Unique
    private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
        return new TranslatableText("options.gamma").append(": ").append(gameOptions.gamma == 0 ? new TranslatableText("options.gamma.min") : gameOptions.gamma == 1 ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
    }
}