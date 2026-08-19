package net.dillon.simplekeybinds.screen;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.KeybindScrollHelper;
import net.dillon.simplekeybinds.config.ConfigurationScreen;
import net.dillon.simplekeybinds.helper.ModConstants;
import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.dillon.simplekeybinds.platform.SimpleKeybindsPlatforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openDebugEntriesScreen;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.simplekeybinds.helper.ModConstants.HAS_UPDATE;
import static net.dillon.simplekeybinds.helper.ModConstants.VERSION;

public class MainMenuScreen extends OptionsSubScreen {

    public MainMenuScreen(Screen lastScreen) {
        super(lastScreen, Minecraft.getInstance().options, Component.translatable("simplekeybinds.menu.title"));
    }

    @Override
    protected void init() {
        super.init();

        this.list.addHeader(Component.translatable("simplekeybinds.menu.options"));
        this.list.addBig(
                Button.builder(Component.translatable("simplekeybinds.menu.keybinds"), button -> {
                    KeybindScrollHelper.request(ModKeyMappings.DEFAULT_CATEGORY);
                    openScreen(new KeyBindsScreen(this, Minecraft.getInstance().options));
                }).tooltip(
                        Tooltip.create(Component.translatable("simplekeybinds.menu.keybinds.tooltip"))
                ).build()
        );
        this.list.addSmall(
                List.of(
                        Button.builder(Component.translatable("simplekeybinds.menu.settings"), button -> ClientTasks.tryOpenYaclScreen(
                                () -> ConfigurationScreen.configScreen().generateScreen(this),
                                Component.translatable("simplekeybinds.title")
                        )).build(),

                        Button.builder(Component.translatable("simplekeybinds.menu.debug_huds"), button -> openDebugEntriesScreen(this, "simplekeybinds")).build()
        ));
    }

    /**
     * Renders the tooltip for the done button.
     */
    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float deltaTicks) {
        super.extractRenderState(graphics, mouseX, mouseY, deltaTicks);

        ClientTasks.drawModInfo(
                graphics,
                this,
                VERSION,
                SimpleKeybindsPlatforms.getPlatform().logoWidth().getWidthModifier(),
                ModConstants.LOGO,
                HAS_UPDATE
        );
    }

    /**
     * Required method.
     */
    @Override
    protected void addOptions() {
    }
}