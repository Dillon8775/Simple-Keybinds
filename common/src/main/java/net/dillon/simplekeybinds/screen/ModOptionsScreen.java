package net.dillon.simplekeybinds.screen;

import net.dillon.simplekeybinds.platform.ModReferences;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.client.ModernWidgetOptions.createOption;

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
                createOption(ListOptions.autoBrightness()),

                createOption(ListOptions.messages())
        };
    }

    @Override
    protected void activateButtons() {
        this.fog.active = !ModReferences.isModLoaded(ModReferences.QUALITY_OF_QUESO);
    }
}