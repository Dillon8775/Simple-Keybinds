package net.dillon.simplekeybinds.screen;

import com.mojang.serialization.Codec;
import net.dillon.dillonlib.client.ModernWidgetOptions;
import net.dillon.simplekeybinds.option.Messages;
import net.dillon.simplekeybinds.option.ModClientOptions;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

import static net.dillon.simplekeybinds.option.OptionInstances.client;

public class ListOptions {

    public static OptionInstance<Boolean> fog() {
        return ModernWidgetOptions.createSimpleBooleanOption(
                "simplekeybinds.options.fog",
                true,
                client().fog,
                ModClientOptions.INSTANCE,
                (options, newValue) -> options.fog = newValue
        );
    }

    public static OptionInstance<Boolean> autoBrightness() {
        return ModernWidgetOptions.createSimpleBooleanOption(
                "simplekeybinds.options.auto_brightness",
                true,
                client().autoBrightness,
                ModClientOptions.INSTANCE,
                (options, aBoolean) -> options.autoBrightness = aBoolean
        );
    }

    public static OptionInstance<Messages> messages() {
        return new OptionInstance<>("simplekeybinds.options.messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.messages.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(Messages.values()),
                        Codec.INT.xmap(Messages::byId, Messages::getId)),
                client().messages,
                value -> client().messages = value);
    }
}