package net.dillon.simplekeybinds.platform.client;

import net.dillon.dillonlib.client.component.PlatformMenuButton;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.simplekeybinds.helper.ModConstants;
import net.dillon.simplekeybinds.helper.ModHelper;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.getScreen;

public class ClientSimpleKeybindsPlatform extends ClientModPlatform {

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
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