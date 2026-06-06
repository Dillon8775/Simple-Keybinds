package net.dillon.simplekeybinds.platform;

import net.blay09.mods.balm.Balm;
import net.dillon.simplekeybinds.util.MixinPluginUtil;

import java.io.File;

public interface PlatformHelper {

    /**
     * @return the mod version.
     */
    default String getModVersion() {
        return "1.4.1";
    }

    /**
     * @return if the platform is on NeoForged.
     */
    @Deprecated
    default boolean isNeoForged() {
        return Balm.platform().name().equals("neoforge");
    }

    /**
     * Gets the config directory for the supported platform.
     */
    File getConfigDir(String fileName);

    /**
     * @return if a mod is loaded on a specific platform. Used only in {@link MixinPluginUtil}.
     */
    boolean isModLoaded(String modId);
}