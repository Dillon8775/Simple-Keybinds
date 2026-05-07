package net.dillon.simplekeybinds.screen;

import net.dillon.simplekeybinds.util.ModTexts;
import net.dillon.simplekeybinds.util.ModUtil;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;

import static net.dillon.simplekeybinds.util.ModUtil.options;

public class ListOptions {
    public static final OptionInstance.CaptionBasedToString<Boolean> ON_OFF_TEXT = (p_231544_, p_231545_) -> p_231545_
            ? ModTexts.ON
            : ModTexts.OFF;

    public static OptionInstance<Boolean> fog() {
        return OptionInstance.createBoolean("simplekeybinds.options.fog",
                ModUtil.qualityOfQuesoLoaded()
                        ? OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.fog.disabled"))
                        : OptionInstance.noTooltip(),
                ON_OFF_TEXT, options().fog, value -> options().fog = value);
    }

    public static OptionInstance<Boolean> autoBrightness() {
        return OptionInstance.createBoolean("simplekeybinds.options.auto_brightness", OptionInstance.cachedConstantTooltip(Component.translatable("simplekeybinds.options.auto_brightness.tooltip")),
                ON_OFF_TEXT, options().autoBrightness, value -> options().autoBrightness = value);
    }
}