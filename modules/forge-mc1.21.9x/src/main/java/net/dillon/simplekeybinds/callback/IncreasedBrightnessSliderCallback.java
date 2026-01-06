package net.dillon.simplekeybinds.callback;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.minecraft.client.OptionInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public enum IncreasedBrightnessSliderCallback implements OptionInstance.SliderableValueSet<Double> {
    INSTANCE;

    @Override
    public @NotNull Optional<Double> validateValue(@NotNull Double aDouble) {
        return aDouble >= SimpleKeybinds.minBrightness && aDouble <= SimpleKeybinds.maxBrightness ? Optional.of(aDouble) : Optional.empty();
    }

    @Override
    public double toSliderValue(@NotNull Double aDouble) {
        double range = SimpleKeybinds.maxBrightness - SimpleKeybinds.minBrightness;
        double offset = SimpleKeybinds.minBrightness;
        return (aDouble - offset) / range;
    }

    @Override
    public @NotNull Double fromSliderValue(double v) {
        double range = SimpleKeybinds.maxBrightness - SimpleKeybinds.minBrightness;
        double offset = SimpleKeybinds.minBrightness;
        return v * range + offset;
    }

    @Override
    public @NotNull Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(SimpleKeybinds.minBrightness, SimpleKeybinds.maxBrightness), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}