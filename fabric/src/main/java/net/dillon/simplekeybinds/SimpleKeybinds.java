package net.dillon.simplekeybinds;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.dillon.simplekeybinds.event.FabricClientEvents;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.dillon.simplekeybinds.keybind.ModKeyMappings;
import net.dillon.simplekeybinds.main.ClientMain;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FabricClientEvents.registerConnectionChecks();
		ModKeyMappings.initKeybinds();

		Balm.initializeMod(ModHelper.MOD_ID, FabricLoadContext.INSTANCE, ClientMain::cInitialize);
	}
}