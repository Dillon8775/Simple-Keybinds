package net.dillon.simplekeybinds.platform;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public File getConfigDir(String fileName) {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), fileName);
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}
