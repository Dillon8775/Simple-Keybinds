package net.dillon.simplekeybinds;

import net.dillon.simplekeybinds.event.ClientEvents;
import net.dillon.simplekeybinds.util.ModUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Simple Keybinds");

	@Override
	public void onInitializeClient() {
		ClientEvents.registerKeybinds();

		ModUtil.initializeSuccess();
	}
}