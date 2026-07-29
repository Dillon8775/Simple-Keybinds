package net.dillon.simplekeybinds.platform;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.ModReference;

public class ModReferences {
    public static final ModReference QUALITY_OF_QUESO = new ModReference("qualityofqueso");
    public static final ModReference SPEEDRUNNER_MOD = new ModReference("speedrunnermod");

    public static boolean isModLoaded(ModReference reference) {
        return Platforms.getDillonLibMixinPlatform().isModLoaded(reference);
    }
}