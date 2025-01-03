package net.dillon.simplekeybinds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {

	/**
	 * The main entrypoint.
	 */
	@Override
	public void onInitializeClient() {
		SimpleKeybindsCore.LOGGER.info("Simple Keybinds mod has successfully initialized.");
	}
}