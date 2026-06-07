package net.dillon.simplekeybinds.screen;

import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.option.Messages;
import net.dillon.simplekeybinds.util.ModTexts;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

import static net.dillon.simplekeybinds.helper.ModHelper.options;

public class ListOptions {
    public static final OptionInstance.CaptionBasedToString<Boolean> ON_OFF_TEXT = (p_231544_, p_231545_) -> p_231545_
            ? ModTexts.ON
            : ModTexts.OFF;

    public static OptionInstance<Boolean> fog() {
        return OptionInstance.createBoolean("simplekeybinds.options.fog",
                ModHelper.qualityOfQuesoLoaded()
                        ? OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.fog.disabled"))
                        : OptionInstance.noTooltip(),
                ON_OFF_TEXT, options().fog, value -> options().fog = value);
    }

    public static OptionInstance<Boolean> autoBrightness() {
        return OptionInstance.createBoolean("simplekeybinds.options.auto_brightness", OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.auto_brightness.tooltip")),
                ON_OFF_TEXT, options().autoBrightness, value -> options().autoBrightness = value);
    }

    public static OptionInstance<Messages> messages() {
        return new OptionInstance<>("simplekeybinds.options.messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.messages.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(Messages.values()),
                        Codec.INT.xmap(Messages::byId, Messages::getId)),
                options().messages,
                value -> options().messages = value);
    }
}