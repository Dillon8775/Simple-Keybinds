package net.dillon.simplekeybinds.main;

import net.blay09.mods.balm.core.BalmRegistrars;
import net.dillon.dillonlib.client.command.ReloadConfigCommand;
import net.dillon.simplekeybinds.helper.ModConstants;
import net.dillon.simplekeybinds.option.ModClientOptions;

import static net.dillon.simplekeybinds.helper.ModHelper.initializeSuccess;

/**
 * The client entrypoint for Simple Keybinds.
 */
public class ClientMain {

    public static void cInitialize(BalmRegistrars registrars) {
        ModClientOptions.INSTANCE.load();
        initializeSuccess();

        ReloadConfigCommand.createReloadable(
                ModConstants.MOD_ID,
                ModClientOptions.INSTANCE
        );
    }
}