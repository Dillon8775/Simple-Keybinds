package net.dillon.simplekeybinds.util;

import net.blay09.mods.balm.api.Balm;
import net.dillon.simplekeybinds.option.ModOptions;
import net.dillon.simplekeybinds.platform.MultiLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModUtil {
    public static final String MOD_ID = "simplekeybinds";
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
     * Sends a {@code warning} message to console.
     */
    public static void warn(String message) {
        LOGGER.warn(message);
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
        info("Simple Keybinds version " + MultiLoader.getPlatform().getModVersion() + " (for " + Balm.getPlatform() + ") loaded successfully!");
    }

    public static float handleFog(LocalPlayer player, FogType fogtype, FogRenderer.FogMode fogMode, float f, boolean bl) {
        boolean nether = player.level().dimension() == Level.NETHER;
        if (bl && !nether) {
            return Math.min(f, 192.0F) * 0.5F;
        }

        if (!player.hasEffect(MobEffects.BLINDNESS) &&
                !player.hasEffect(MobEffects.DARKNESS) &&
                fogMode != FogRenderer.FogMode.FOG_SKY &&
                fogtype != FogType.WATER &&
                fogtype != FogType.LAVA &&
                fogtype != FogType.POWDER_SNOW) {
            return Integer.MAX_VALUE;
        }

        return f;
    }

    public static Component toComponentString(Double gamma) {
        return Component.translatable("options.gamma").append(": ").append(Component.literal(Math.round(gamma * 100) + "%"));
    }

    public static void onValueUpdate(Double brightness) {
        Minecraft.getInstance().options.gamma().set(brightness);
    }
}