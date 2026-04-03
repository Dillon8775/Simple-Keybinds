package net.dillon.simplekeybinds.main;

import net.blay09.mods.balm.core.BalmRegistrars;

import static net.dillon.simplekeybinds.option.ModOptions.loadConfig;
import static net.dillon.simplekeybinds.util.ModUtil.initializeSuccess;

/**
 * The client entrypoint for Simple Keybinds.
 */
public class ClientMain {

    public static void cInitialize(BalmRegistrars registrars) {
        loadConfig();
        initializeSuccess();
    }
}