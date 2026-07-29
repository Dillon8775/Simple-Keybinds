package net.dillon.simplekeybinds.screen;

import com.mojang.serialization.Codec;
import net.dillon.dillonlib.client.ModernWidgetOptions;
import net.dillon.simplekeybinds.option.Messages;
import net.dillon.simplekeybinds.option.ModOptions;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

import static net.dillon.simplekeybinds.helper.ModHelper.optionsInstance;

public class ListOptions {

    public static OptionInstance<Boolean> fog() {
        return ModernWidgetOptions.createSimpleBooleanOption(
                "simplekeybinds.options.fog",
                true,
                optionsInstance().fog,
                ModOptions.INSTANCE,
                (options, newValue) -> options.fog = newValue
        );
    }

    public static OptionInstance<Boolean> autoBrightness() {
        return ModernWidgetOptions.createSimpleBooleanOption(
                "simplekeybinds.options.auto_brightness",
                true,
                optionsInstance().autoBrightness,
                ModOptions.INSTANCE,
                (options, aBoolean) -> options.autoBrightness = aBoolean
        );
    }

    public static OptionInstance<Messages> messages() {
        return new OptionInstance<>("simplekeybinds.options.messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.messages.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(Messages.values()),
                        Codec.INT.xmap(Messages::byId, Messages::getId)),
                optionsInstance().messages,
                value -> optionsInstance().messages = value);
    }
}