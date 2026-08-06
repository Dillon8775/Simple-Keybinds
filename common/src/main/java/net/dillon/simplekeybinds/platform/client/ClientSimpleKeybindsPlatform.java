package net.dillon.simplekeybinds.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;

public class ClientSimpleKeybindsPlatform extends ClientModPlatform {

    @Override
    public String modId() {
        return ModHelper.MOD_ID;
    }

    // Unused
    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return null;
    }

    // Unused
    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return false;
    }
}