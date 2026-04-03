package net.dillon.simplekeybinds;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
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
		Balm.initializeMod(ModUtil.MOD_ID, EmptyLoadContext.INSTANCE, ClientMain::cInitialize);

		FabricClientEvents.registerKeybinds();
	}
}