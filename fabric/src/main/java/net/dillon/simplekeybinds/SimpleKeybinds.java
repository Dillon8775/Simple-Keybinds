package net.dillon.simplekeybinds;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.dillon.simplekeybinds.event.FabricClientEvents;
import net.dillon.simplekeybinds.main.ClientMain;
import net.dillon.simplekeybinds.util.ModUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SimpleKeybinds implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Balm.initializeMod(ModUtil.MOD_ID, FabricLoadContext.INSTANCE, ClientMain::cInitialize);

		FabricClientEvents.registerKeybinds();
	}
}