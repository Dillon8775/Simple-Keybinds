package net.dillon.simplekeybinds.main;

import net.blay09.mods.balm.core.BalmRegistrars;

import static net.dillon.simplekeybinds.debug.ModHudEntries.registerDebugEntries;
import static net.dillon.simplekeybinds.helper.ModHelper.initializeSuccess;
import static net.dillon.simplekeybinds.option.ModOptions.loadConfig;

/**
 * The client entrypoint for Simple Keybinds.
 */
public class ClientMain {

    public static void cInitialize(BalmRegistrars registrars) {
        loadConfig();
        initializeSuccess();

        registerDebugEntries();
    }
}