package net.dillon.simplekeybinds;

import net.dillon.simplekeybinds.keybinds.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {
	public static boolean fog = true;
	public static boolean fullBright = false;
	public static final double minBrightness = 0.0D;
	public static final double maxBrightness = 12.0D;
	public static final Logger LOGGER = LogManager.getLogger("Simple Keybinds");

	@Override
	public void onInitializeClient() {
		ModKeybinds.init();
	}
}