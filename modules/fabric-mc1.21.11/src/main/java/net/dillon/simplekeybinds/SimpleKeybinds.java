package net.dillon.simplekeybinds;

import net.dillon.simplekeybinds.keybind.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main entrypoint for the {@code Simple Keybinds} mod.
 */
@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {
	public static boolean fog = true;
	public static boolean fullBright = false;
	public static final double minBrightness = 0.0D;
	public static final double maxBrightness = 12.0D;
    public static double previousBrightness = 1.0D;
	public static double cachedBrightness = 1.0D;

	public static boolean scrolling = false;
	public static boolean muted;
	public static double cachedVolume = 1.0D;

	public static final Logger LOGGER = LogManager.getLogger("Simple Keybinds");

	/**
	 * Initializes all {@code Simple Keybinds.}
	 */
	@Override
	public void onInitializeClient() {
		ModKeybinds.init();
		SimpleKeybinds.LOGGER.info("Simple Keybinds mod has successfully initialized.");
	}

	/**
	 * @return {@code true} if the {@code speedrunner mod} is loaded.
	 */
	public static boolean isSpeedrunnerModLoaded() {
		return FabricLoader.getInstance().isModLoaded("speedrunnermod");
	}
}