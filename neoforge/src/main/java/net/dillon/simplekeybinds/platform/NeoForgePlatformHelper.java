package net.dillon.simplekeybinds.platform;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;

public class NeoForgePlatformHelper implements PlatformHelper {

    @Override
    public File getConfigDir(String fileName) {
        return new File(FMLPaths.CONFIGDIR.get().resolve(fileName).toString());
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FMLLoader.getCurrent().getLoadingModList().getModFileById(modId) != null;
    }
}