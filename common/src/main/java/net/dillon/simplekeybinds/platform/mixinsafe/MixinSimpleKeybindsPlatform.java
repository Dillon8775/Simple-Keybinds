package net.dillon.simplekeybinds.platform.mixinsafe;

import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.simplekeybinds.helper.ModHelper;

public abstract class MixinSimpleKeybindsPlatform extends MixinModPlatform {

    @Override
    public String modId() {
        return ModHelper.MOD_ID;
    }

    @Override
    public boolean shouldApplyFullBright() {
        return true;
    }
}