package net.dillon.simplekeybinds.platform;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.platform.ModPlatform;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.dillonlib.platform.info.LogoWidth;
import net.dillon.dillonlib.platform.info.PlatformName;
import net.dillon.dillonlib.platform.info.PlatformRelease;
import net.dillon.simplekeybinds.helper.ModHelper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class SimpleKeybindsPlatformImpl extends ModPlatform {

    @Override
    public String modId() {
        return ModHelper.MOD_ID;
    }

    @Override
    public @NotNull Logger logger() {
        return ModHelper.LOGGER;
    }

    @Override
    public String modVersion() {
        return Platforms.getCommonPlatform().commonModVersion(ModHelper.MOD_ID);
    }

    @Override
    public @NotNull PlatformName platformName() {
        return Balm.platform().name().equals("fabric") ? PlatformName.FABRIC : PlatformName.NEOFORGE;
    }

    @Override
    public @NotNull PlatformRelease platformRelease() {
        return PlatformRelease.STABLE;
    }

    @Override
    public @NotNull LogoWidth logoWidth() {
        return LogoWidth.DEFAULT;
    }
}