package net.dillon.simplekeybinds;

import net.dillon.simplekeybinds.keybinds.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {
	public static boolean fog = true;
	public static boolean fullBright = false;
	public static final double minBrightness = 0.5D;
	public static final double maxBrightness = 12.0D;
	public static final Logger LOGGER = LogManager.getLogger("Simple Keybinds");

	/**
	 * Initializes the {@code Simple Keybindings.}
	 */
	@Override
	public void onInitializeClient() {
		ModKeybinds.init();
	}

	/**
	 * Returns true if the {@code speedrunner mod} is loaded.
	 */
	public static boolean isSpeedrunnerModLoaded() {
		return FabricLoader.getInstance().isModLoaded("speedrunnermod");
	}
}