package net.dillon.simplekeybinds.platform;

import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.simplekeybinds.helper.ModHelper;

public class SimpleKeybindsPlatformGetter {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, ModHelper.MOD_ID);

    public static ModPlatform get() {
        return PLATFORM;
    }
}