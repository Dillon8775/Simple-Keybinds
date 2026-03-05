package net.dillon.simplekeybinds.callback;

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;

import java.util.Optional;

public enum IncreasedBrightnessSliderCallback implements OptionInstance.SliderableValueSet<Double> {
    INSTANCE;

    @Override
    public double toSliderValue(Double value) {
        return IncreasedBrightnessSliderCallbackBase.toSliderValue(value);
    }

    @Override
    public Double fromSliderValue(double slider) {
        return IncreasedBrightnessSliderCallbackBase.fromSliderValue(slider);
    }

    @Override
    public Optional<Double> validateValue(Double value) {
        return IncreasedBrightnessSliderCallbackBase.validateValue(value);
    }

    @Override
    public Codec<Double> codec() {
        return IncreasedBrightnessSliderCallbackBase.codec();
    }
}