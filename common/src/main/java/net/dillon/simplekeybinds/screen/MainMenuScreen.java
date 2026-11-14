package net.dillon.simplekeybinds.screen;

import net.dillon.dillonlib.client.component.DoneButtonBuilder;
import net.dillon.dillonlib.client.component.GridBuilder;
import net.dillon.dillonlib.client.screen.DillonLibScreen;
import net.dillon.dillonlib.client.util.KeybindScrollHelper;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.widespread.DillonsWidgets;
import net.dillon.simplekeybinds.config.ConfigurationScreen;
import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openDebugEntriesScreen;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.dillonlib.widespread.DillonsWidgets.createMultiLineText;
import static net.dillon.dillonlib.widespread.DillonsWidgets.finalizeHeaderAndFooter;
import static net.dillon.simplekeybinds.helper.ModConstants.MOD_INFO;

public class MainMenuScreen extends DillonLibScreen {

    public MainMenuScreen(Screen lastScreen) {
        super(lastScreen, Component.translatable("simplekeybinds.title"));
    }

    @Override
    public void widgets() {
        AbstractWidget keybinds = Button.builder(Component.translatable("simplekeybinds.menu.keybinds"), b -> {
            KeybindScrollHelper.request(ModKeyMappings.DEFAULT_CATEGORY);
            openScreen(new KeyBindsScreen(this, Minecraft.getInstance().options));
        }).tooltip(
                Tooltip.create(Component.translatable("simplekeybinds.menu.keybinds.tooltip"))
        ).width(200).build();

        AbstractWidget settings = Button.builder(Component.translatable("simplekeybinds.menu.settings"), b -> ClientTasks.tryOpenYaclScreen(
                () -> ConfigurationScreen.configScreen().generateScreen(this),
                Component.translatable("simplekeybinds")
        )).width(200).build();

        AbstractWidget debugHuds = Button.builder(Component.translatable("simplekeybinds.menu.debug_huds"),
                        b -> openDebugEntriesScreen(this, "simplekeybinds"))
                .width(200)
                .build();

        GridBuilder builder = GridBuilder.create(this)
                .setRowSpacing(12)
                .setColumnSpacing(12);

        builder.fillRow(
                createMultiLineText(title),
                builder.alignHorizontallyCenter().paddingBottom(8)
        );

        builder.fillRowAndCenter(keybinds);
        builder.fillRowAndCenter(settings);
        builder.fillRowAndCenter(debugHuds);

        builder.fillRow(
                DillonsWidgets.createVersionLabel(MOD_INFO, this),
                builder.alignHorizontallyCenter().paddingTop(8)
        );

        headerAndFooterLayout.addToFooter(
                DoneButtonBuilder.builder(this)
                        .width(175)
                        .build()
        );
        finalizeHeaderAndFooter(this, headerAndFooterLayout);

        builder.setVerticalAlignment(0.40F).buildAndAlign();
    }
}