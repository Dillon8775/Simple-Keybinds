package net.dillon.simplekeybinds.platform;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public File getConfigDir(String fileName) {
        return new File(FMLPaths.CONFIGDIR.get().resolve(fileName).toString());
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}