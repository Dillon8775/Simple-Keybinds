package net.dillon.simplekeybinds.impl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dillon.simplekeybinds.screen.MainMenuScreen;

/**
 * Implementation for Mod Menu.
 */
public class ModMenuImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MainMenuScreen::new;
    }
}