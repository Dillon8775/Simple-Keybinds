package net.dillon.simplekeybinds.screen;

import net.dillon.simplekeybinds.option.ModListOptions;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModOptionsScreen extends AbstractModOptionsScreen {

    public ModOptionsScreen(Screen lastScreen) {
        super(lastScreen, Component.translatable("simplekeybinds.keybinds"));
    }

    @Override
    protected OptionInstance<?>[] options() {
        return new OptionInstance[]{
                ModListOptions.fog(),
                ModListOptions.autoBrightness()
        };
    }
}