package net.dillon.simplekeybinds;

import net.dillon.simplekeybinds.keybinds.ModKeybinds;
import net.dillon.simplekeybinds.option.ModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {
	public static double minBrightness = 0.5D;
	public static double maxBrightness = 12.0D;
	public static final Logger LOGGER = LogManager.getLogger("simplekeybinds");

	@Override
	public void onInitializeClient() {
		ModOptions.loadConfig();
		ModKeybinds.init();
		LOGGER.info("Simple Keybinds mod has been loaded.");
	}

	public static ModOptions options() {
		return ModOptions.OPTIONS;
	}
}