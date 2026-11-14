package net.dillon.simplekeybinds.event;

import net.dillon.dillonlib.task.CommonTasks;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;

import static net.dillon.simplekeybinds.helper.ModConstants.MOD_INFO;

/**
 * Client events for Simple Keybinds.
 */
public class ClientEvents {

    public static void onPlayerJoin(Connection connection, LocalPlayer player) {
        CommonTasks.sendUpdateMessage(MOD_INFO, player);
    }
}