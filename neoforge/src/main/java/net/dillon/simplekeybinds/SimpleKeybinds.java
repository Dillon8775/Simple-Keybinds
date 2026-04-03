package net.dillon.simplekeybinds;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.dillon.simplekeybinds.main.ClientMain;
import net.dillon.simplekeybinds.screen.ModOptionsScreen;
import net.dillon.simplekeybinds.util.ModUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ModUtil.MOD_ID, dist = Dist.CLIENT)
public final class SimpleKeybinds {

    public SimpleKeybinds(IEventBus modEventBus, ModContainer container) {
        final var context = new NeoForgeLoadContext(modEventBus);
        Balm.initializeMod(ModUtil.MOD_ID, context, ClientMain::cInitialize);

        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mc, parent) -> new ModOptionsScreen(parent)
        );
    }
}