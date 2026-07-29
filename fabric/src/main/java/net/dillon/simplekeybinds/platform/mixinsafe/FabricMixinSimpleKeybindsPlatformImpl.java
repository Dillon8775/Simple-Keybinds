package net.dillon.simplekeybinds.platform.mixinsafe;

import net.dillon.dillonlib.platform.info.ModReference;
import net.fabricmc.loader.api.FabricLoader;

public class FabricMixinSimpleKeybindsPlatformImpl extends MixinSimpleKeybindsPlatform {

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FabricLoader.getInstance().isModLoaded(mod.modId());
    }
}