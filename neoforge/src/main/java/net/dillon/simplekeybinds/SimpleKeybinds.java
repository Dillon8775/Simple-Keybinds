package net.dillon.simplekeybinds;

import com.mojang.logging.LogUtils;
import net.dillon.simplekeybinds.option.ModOptions;
import net.dillon.simplekeybinds.screen.ModOptionsScreen;
import net.dillon.simplekeybinds.util.ModUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(value = SimpleKeybinds.MOD_ID, dist = Dist.CLIENT)
public final class SimpleKeybinds {
    public static final String MOD_ID = "simplekeybinds";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleKeybinds(IEventBus modEventBus, ModContainer container) {
        ModOptions.loadConfig();

        ModUtil.initializeSuccess();

        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mc, parent) -> new ModOptionsScreen(parent)
        );
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}