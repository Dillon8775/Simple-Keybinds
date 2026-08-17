package net.dillon.simplekeybinds.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.dillon.dillonlib.core.DillonLibModReferences;
import net.dillon.simplekeybinds.option.MenuButton;
import net.dillon.simplekeybinds.option.Messages;
import net.dillon.simplekeybinds.option.ModClientOptions;
import net.minecraft.network.chat.Component;

import static net.dillon.simplekeybinds.option.OptionInstances.client;

/**
 * The main configuration screen for Simple Keybinds.
 */
public class ConfigurationScreen {

    public static YetAnotherConfigLib configScreen() {
        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("simplekeybinds.title"))
                .category(
                        ConfigCategory.createBuilder()
                                .name(Component.translatable("simplekeybinds.title"))
                                .tooltip(Component.translatable("simplekeybinds.title.tooltip"))
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Component.translatable("simplekeybinds.options.general"))
                                                .description(OptionDescription.of(Component.translatable("simplekeybinds.options.general.description")))
                                                .option(
                                                        Option.<Messages>createBuilder()
                                                                .name(Component.translatable("simplekeybinds.options.messages"))
                                                                .description(OptionDescription.of(Component.translatable("simplekeybinds.options.messages.description")))
                                                                .binding(Messages.OVERLAY, () -> client().messages, v -> client().messages = v)
                                                                .controller(o -> EnumControllerBuilder.create(o)
                                                                        .enumClass(Messages.class)
                                                                        .formatValue(v -> Component.translatable(v.getSerializedName())))
                                                                .build()
                                                )
                                                .option(
                                                        Option.<MenuButton>createBuilder()
                                                                .name(Component.translatable("simplekeybinds.options.menu_button"))
                                                                .description(OptionDescription.of(Component.translatable("simplekeybinds.options.menu_button.description")))
                                                                .binding(MenuButton.TITLE_ONLY, () -> client().menuButton, v -> client().menuButton = v)
                                                                .controller(o -> EnumControllerBuilder.create(o)
                                                                        .enumClass(MenuButton.class)
                                                                        .formatValue(v -> Component.translatable(v.getSerializedName())))
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Component.translatable("simplekeybinds.options.fog"))
                                                                .description(OptionDescription.of(Component.translatable("simplekeybinds.options.fog.description")))
                                                                .binding(true, () -> client().fog, v -> client().fog = v)
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .available(!DillonLibModReferences.isModLoaded(DillonLibModReferences.QUALITY_OF_QUESO))
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Component.translatable("simplekeybinds.options.auto_brightness"))
                                                                .description(OptionDescription.of(Component.translatable("simplekeybinds.options.auto_brightness.description")))
                                                                .binding(false, () -> client().autoBrightness, v -> client().autoBrightness = v)
                                                                .controller(BooleanControllerBuilderImpl::new)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .save(() -> {
                    ModClientOptions.INSTANCE.save();
                })
                .build();
    }
}