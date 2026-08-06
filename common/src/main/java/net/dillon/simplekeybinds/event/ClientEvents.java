package net.dillon.simplekeybinds.event;

import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;

/**
 * Client events for Simple Keybinds.
 */
public class ClientEvents {

    public static void onPlayerJoin(Connection connection, LocalPlayer player) {
        if (ModHelper.HAS_UPDATE) {
            CommonTasks.sendUpdateMessage(player,
                    Component.translatable("simplekeybinds.keybinds"),
                    "https://modrinth.com/mod/simple-keybinds/versions",
                    TextColor.WHITE.getValue());
        }
    }
}