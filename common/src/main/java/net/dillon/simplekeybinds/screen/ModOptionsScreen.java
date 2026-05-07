package net.dillon.simplekeybinds.screen;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.simplekeybinds.util.ModUtil.qualityOfQuesoLoaded;

public class ModOptionsScreen extends AbstractModOptionsScreen {
    private AbstractWidget fog;

    public ModOptionsScreen(Screen lastScreen) {
        super(lastScreen, Component.translatable("simplekeybinds.keybinds"));
    }

    @Override
    protected AbstractWidget[] options() {
        this.fog = createOption(ListOptions.fog());

        return new AbstractWidget[]{
                this.fog,
                createOption(ListOptions.autoBrightness())
        };
    }

    @Override
    protected void activateButtons() {
        this.fog.active = !qualityOfQuesoLoaded();
    }
}