package net.dillon.simplekeybinds.callback;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.core.SimpleKeybindsCore;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;

public enum IncreasedBrightnessSliderCallback implements SimpleOption.SliderCallbacks<Double> {
    INSTANCE;

    @Override
    public Optional<Double> validate(Double double_) {
        return double_ >= SimpleKeybindsCore.minBrightness && double_ <= SimpleKeybindsCore.maxBrightness ? Optional.of(double_) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double double_) {
        double range = SimpleKeybindsCore.maxBrightness - SimpleKeybindsCore.minBrightness;
        double offset = SimpleKeybindsCore.minBrightness;
        return (double_ - offset) / range;
    }

    @Override
    public Double toValue(double d) {
        double range = SimpleKeybindsCore.maxBrightness - SimpleKeybindsCore.minBrightness;
        double offset = SimpleKeybindsCore.minBrightness;
        return d * range + offset;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(SimpleKeybindsCore.minBrightness, SimpleKeybindsCore.maxBrightness), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}