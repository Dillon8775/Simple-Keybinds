package net.dillon.simplekeybinds;

import com.mojang.logging.LogUtils;
import net.dillon.simplekeybinds.util.ModUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod(SimpleKeybinds.MOD_ID)
public final class SimpleKeybinds {
    public static final String MOD_ID = "simplekeybinds";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleKeybinds(IEventBus iEventBus) {
        ModUtil.initializeSuccess();
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}