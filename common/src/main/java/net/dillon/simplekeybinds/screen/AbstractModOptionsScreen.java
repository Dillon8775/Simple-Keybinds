package net.dillon.simplekeybinds.screen;

import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.simplekeybinds.helper.ModConstants;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.platform.SimpleKeybindsPlatforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;

import static net.dillon.simplekeybinds.helper.ModConstants.HAS_UPDATE;
import static net.dillon.simplekeybinds.helper.ModConstants.VERSION;

public abstract class AbstractModOptionsScreen extends OptionsSubScreen {
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

    public AbstractModOptionsScreen(Screen lastScreen, Component title) {
        super(lastScreen, Minecraft.getInstance().options, title);
    }

    /**
     * The list of {@link OptionInstance}s that should be added to the screen.
     */
    protected abstract AbstractWidget[] options();

    @Override
    protected void init() {
        super.init();
        if (this.addOptionsByDefault()) {
            this.list.addSmall(List.of(this.options()));
        }

        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());
    }

    /**
     * Renders the tooltip for the done button.
     */
    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float deltaTicks) {
        this.activateButtons();
        super.extractRenderState(graphics, mouseX, mouseY, deltaTicks);

        ClientTasks.drawModInfo(
                graphics,
                this,
                VERSION,
                SimpleKeybindsPlatforms.getPlatform().logoWidth().getWidthModifier(),
                Identifier.fromNamespaceAndPath("simplekeybinds", "textures/gui/sprites/simplekeybinds.png"),
                HAS_UPDATE
        );
    }

    @Override
    public void onClose() {
        ModConstants.LOGGER.info("Saved changes.");
        super.onClose();
    }

    /**
     * Determines if buttons can be active.
     */
    protected void activateButtons() {
    }

    /**
     * @return if all options in the {@link AbstractModOptionsScreen#options()} method should be added by default when calling {@code super.init().}
     */
    protected boolean addOptionsByDefault() {
        return true;
    }

    /**
     * Required method.
     */
    @Override
    protected void addOptions() {
    }
}