package net.dillon.simplekeybinds.helper;

import net.blay09.mods.balm.Balm;
import net.dillon.simplekeybinds.option.ModOptions;
import net.dillon.simplekeybinds.platform.MultiLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Unique;

public class ModHelper {
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
     * @return an identifier with the {@code Simple Keybinds} namespace.
     */
    public static Identifier ofSimpleKeybinds(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /**
     * Sends the successfully initialized message.
     */
    public static void initializeSuccess() {
        info("Simple Keybinds version " + MultiLoader.getPlatform().getModVersion() + " (for " + Balm.platform().name() + ") loaded successfully!");
    }

    /**
     * @return if the {@code Quality of Queso mod} is loaded.

     */
    public static boolean qualityOfQuesoLoaded() {
        return Balm.platform().isModLoaded("qualityofqueso");
    }

    /**
     * Mutes the game.
     */
    public static void mute(Options options) {
        if (scrolling) {
            System.out.println("what??");
            return;
        }

        double currentVolume = options.getSoundSourceOptionInstance(SoundSource.MASTER).get();
        if (currentVolume == 0.0F) {
            unmute(options);
            return;
        }

        cachedVolume = currentVolume;
        muted = true;

        options.getSoundSourceOptionInstance(SoundSource.MASTER).set(0D);
    }

    /**
     * Unmutes the game.
     */
    @Unique
    public static void unmute(Options options) {
        options.getSoundSourceOptionInstance(SoundSource.MASTER).set((cachedVolume == 0.0D ? 0.5D : cachedVolume));
        muted = false;
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