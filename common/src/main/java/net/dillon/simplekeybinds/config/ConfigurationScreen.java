package net.dillon.simplekeybinds.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.dillon.dillonlib.client.util.DebugTextAlignment;
import net.dillon.dillonlib.core.DillonLibModReferences;
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
                .title(Component.translatable("simplekeybinds"))
                .category(
                        ConfigCategory.createBuilder()
                                .name(Component.translatable("simplekeybinds.options.title"))
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
                                .option(
                                        Option.<DebugTextAlignment>createBuilder()
                                                .name(Component.translatable("dillonlib.option.debug_text_alignment"))
                                                .description(OptionDescription.of(Component.translatable("dillonlib.option.debug_text_alignment.description")))
                                                .binding(DebugTextAlignment.AUTO, () -> client().debugTextAlignment, v -> client().debugTextAlignment = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(DebugTextAlignment.class)
                                                        .formatValue(v -> Component.translatable(v.getSerializedName())))
                                                .flag(OptionFlag.GAME_RESTART)
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