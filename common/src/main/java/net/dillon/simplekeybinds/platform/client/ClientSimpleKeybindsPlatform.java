package net.dillon.simplekeybinds.platform.client;

import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.simplekeybinds.debug.BrightnessDebugEntry;
import net.dillon.simplekeybinds.debug.ModDebugScreenEntries;
import net.dillon.simplekeybinds.helper.ModConstants;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.getScreen;

public class ClientSimpleKeybindsPlatform extends ClientModPlatform {

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
    }

    @Override
    public void registerDebugEntries(WritableRegistry<DebugScreenEntry> registry) {
        Registry.register(registry, ModDebugScreenEntries.BRIGHTNESS, new BrightnessDebugEntry());
    }

    @Override
    public List<PlatformMenuButton> menuButtons() {
        return List.of(
                PlatformMenuButton.builder(
                        ModHelper.createMenuButton(getScreen())
                ).build()
        );
    }
}