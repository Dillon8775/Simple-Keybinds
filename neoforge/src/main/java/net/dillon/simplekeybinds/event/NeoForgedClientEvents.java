package net.dillon.simplekeybinds.event;

import net.dillon.simplekeybinds.helper.ModHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

@EventBusSubscriber(modid = ModHelper.MOD_ID, value = Dist.CLIENT)
public class NeoForgedClientEvents {

    @SubscribeEvent
    public static void onClientLoggingIn(ClientPlayerNetworkEvent.LoggingIn event) {
        ClientEvents.onPlayerJoin(event.getConnection(), event.getPlayer());
    }
}