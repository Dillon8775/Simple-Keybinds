package net.dillon.simplekeybinds.screen;

import net.dillon.simplekeybinds.option.ModOptions;
import net.dillon.simplekeybinds.util.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public abstract class AbstractModOptionsScreen extends OptionsSubScreen {
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 61, 33);

    public AbstractModOptionsScreen(Screen lastScreen, Component title) {
        super(lastScreen, Minecraft.getInstance().options, title);
    }

    /**
     * The list of {@link OptionInstance}s that should be added to the screen.
     */
    protected abstract OptionInstance<?>[] options();

    @Override
    protected void init() {
        super.init();
        if (this.addOptionsByDefault()) {
            this.list.addSmall(this.options());
        }

        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> this.onClose()).width(200).build());
    }

    @Override
    public void onClose() {
        ModOptions.saveConfig();
        ModUtil.info("Saved changes.");
        super.onClose();
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