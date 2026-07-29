package net.dillon.simplekeybinds.platform.mixinsafe;

import net.dillon.dillonlib.platform.info.ModReference;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgeMixinSimpleKeybindsPlatformImpl extends MixinSimpleKeybindsPlatform {

    @Override
    public boolean isModLoaded(ModReference mod) {
        return FMLLoader.getCurrent().getLoadingModList().getModFileById(mod.modId()) != null;
    }
}