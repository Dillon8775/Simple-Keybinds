package net.dillon.simplekeybinds;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.dillon.simplekeybinds.main.ClientMain;
import net.dillon.simplekeybinds.screen.ModOptionsScreen;
import net.dillon.simplekeybinds.util.ModUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = ModUtil.MOD_ID)
public final class SimpleKeybinds {

    public SimpleKeybinds(FMLJavaModLoadingContext context) {
        Balm.initializeMod(ModUtil.MOD_ID, EmptyLoadContext.INSTANCE, ClientMain::cInitialize);

        MinecraftForge.registerConfigScreen(ModOptionsScreen::new);
    }
}