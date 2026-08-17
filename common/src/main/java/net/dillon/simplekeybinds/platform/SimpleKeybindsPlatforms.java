package net.dillon.simplekeybinds.platform;

import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.PlatformLoader;
import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.simplekeybinds.helper.ModConstants;

public class SimpleKeybindsPlatforms {
    private static final ModPlatform PLATFORM = PlatformLoader.load(ModPlatform.class, ModConstants.MOD_ID);
    private static final MixinModPlatform MIXIN_PLATFORM = PlatformLoader.load(MixinModPlatform.class, ModConstants.MOD_ID);

    public static ModPlatform getPlatform() {
        return PLATFORM;
    }

    public static MixinModPlatform getMixinPlatform() {
        return MIXIN_PLATFORM;
    }
}