package net.dillon.simplekeybinds.util;

import net.dillon.simplekeybinds.option.ModOptions;
import net.dillon.simplekeybinds.platform.MultiLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("Simple Keybinds");
    public static boolean fullBright = false;
    public static final double minBrightness = 0.0D;
    public static final double maxBrightness = 12.0D;
    public static double previousBrightness = 1.0D;

    public static boolean scrolling = false;
    public static boolean muted;
    public static double cachedVolume = 1.0D;

    /**
     * Sends a message to console.
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Returns the options.
     */
    public static ModOptions options() {
        return ModOptions.OPTIONS;
    }

    /**
     * Sends the successfully initialized message.
     */
    public static void initializeSuccess() {
        info("Simple Keybinds version " + MultiLoader.PLATFORM.getModVersion() + " (for " + MultiLoader.PLATFORM.getPlatformName() + ") loaded successfully!");
    }

    public static void handleFog(Entity entity, FogType fogtype, FogData fogData) {
        if (entity instanceof LivingEntity livingEntity &&
                !livingEntity.hasEffect(MobEffects.BLINDNESS) &&
                !livingEntity.hasEffect(MobEffects.DARKNESS) &&
                !options().fog &&
                fogtype != FogType.WATER &&
                fogtype != FogType.LAVA &&
                fogtype != FogType.POWDER_SNOW) {
            fogData.renderDistanceEnd = Integer.MAX_VALUE;
            fogData.environmentalEnd = Integer.MAX_VALUE;
        }
    }

    public static Component toComponentString(Double gamma) {
        return Component.translatable("options.gamma").append(": ").append(Component.literal(Math.round(gamma * 100) + "%"));
    }

    public static void onValueUpdate(Double brightness) {
        Minecraft.getInstance().options.gamma().set(brightness);
    }
}