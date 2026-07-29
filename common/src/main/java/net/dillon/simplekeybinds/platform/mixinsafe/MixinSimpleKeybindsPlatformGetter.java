package net.dillon.simplekeybinds.platform.mixinsafe;

import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.simplekeybinds.helper.ModHelper;

public class MixinSimpleKeybindsPlatformGetter {
    private static final MixinModPlatform PLATFORM = PlatformLoader.load(MixinModPlatform.class, ModHelper.MOD_ID);

    public static MixinModPlatform get() {
        return PLATFORM;
    }
}