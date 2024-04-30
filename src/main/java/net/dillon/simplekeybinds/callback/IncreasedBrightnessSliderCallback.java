package net.dillon.simplekeybinds.callback;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;

public enum IncreasedBrightnessSliderCallback implements SimpleOption.SliderCallbacks<Double> {
    INSTANCE;

    @Override
    public Optional<Double> validate(Double double_) {
        return double_ >= SimpleKeybinds.minBrightness && double_ <= SimpleKeybinds.maxBrightness ? Optional.of(double_) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double double_) {
        double range = SimpleKeybinds.maxBrightness - SimpleKeybinds.minBrightness;
        double offset = SimpleKeybinds.minBrightness;
        return (double_ - offset) / range;
    }

    @Override
    public Double toValue(double d) {
        double range = SimpleKeybinds.maxBrightness - SimpleKeybinds.minBrightness;
        double offset = SimpleKeybinds.minBrightness;
        return d * range + offset;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(SimpleKeybinds.minBrightness, SimpleKeybinds.maxBrightness), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}