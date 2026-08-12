package net.dillon.simplekeybinds.platform.mixinsafe;

import net.dillon.dillonlib.platform.mixinsafe.MixinModPlatform;
import net.dillon.simplekeybinds.helper.ModConstants;

public class MixinSimpleKeybindsPlatform extends MixinModPlatform {

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
    }

    @Override
    public boolean shouldApplyFullBright() {
        return true;
    }
}