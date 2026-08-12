package net.dillon.simplekeybinds.main;

import net.blay09.mods.balm.core.BalmRegistrars;
import net.dillon.simplekeybinds.option.ModClientOptions;

import static net.dillon.simplekeybinds.debug.ModHudEntries.registerDebugEntries;
import static net.dillon.simplekeybinds.helper.ModHelper.initializeSuccess;

/**
 * The client entrypoint for Simple Keybinds.
 */
public class ClientMain {

    public static void cInitialize(BalmRegistrars registrars) {
        ModClientOptions.INSTANCE.load();
        initializeSuccess();
        registerDebugEntries();
    }
}