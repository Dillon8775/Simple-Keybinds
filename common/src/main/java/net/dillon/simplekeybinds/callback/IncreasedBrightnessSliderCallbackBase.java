package net.dillon.simplekeybinds.callback;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.helper.ModHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class IncreasedBrightnessSliderCallbackBase {

    public static @NotNull Optional<Double> validateValue(@NotNull Double aDouble) {
        return aDouble >= ModHelper.minBrightness && aDouble <= ModHelper.maxBrightness ? Optional.of(aDouble) : Optional.empty();
    }

    public static double toSliderValue(@NotNull Double aDouble) {
        double range = ModHelper.maxBrightness - ModHelper.minBrightness;
        double offset = ModHelper.minBrightness;
        return (aDouble - offset) / range;
    }

    public static @NotNull Double fromSliderValue(double v) {
        double range = ModHelper.maxBrightness - ModHelper.minBrightness;
        double offset = ModHelper.minBrightness;
        return v * range + offset;
    }

    public static @NotNull Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(ModHelper.minBrightness, ModHelper.maxBrightness), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}