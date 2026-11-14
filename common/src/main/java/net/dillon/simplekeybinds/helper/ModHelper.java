package net.dillon.simplekeybinds.helper;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.client.component.UpdatableSpriteButton;
import net.dillon.simplekeybinds.platform.SimpleKeybindsPlatforms;
import net.dillon.simplekeybinds.screen.MainMenuScreen;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;

import static net.dillon.dillonlib.task.ClientTasks.getMinecraft;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.simplekeybinds.helper.ModConstants.MOD_ID;
import static net.dillon.simplekeybinds.helper.ModConstants.MOD_INFO;
import static net.dillon.simplekeybinds.option.OptionInstances.client;

public class ModHelper {
    public static boolean fullBright = false;
    public static final double minBrightness = 0.0D;
    public static final double maxBrightness = 12.0D;
    public static double previousBrightness = 1.0D;

    public static boolean scrolling = false;
    public static boolean muted;
    public static double cachedVolume = 1.0D;

    /**
     * @return an identifier with the {@code Simple Keybinds} namespace.
     */
    public static Identifier simpleKeybindsIdentifier(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /**
     * @return the menu button for Simple Keybinds.
     */
    public static UpdatableSpriteButton createMenuButton(Screen parent) {
        return UpdatableSpriteButton.builder(
                "Simple Keybinds Main Menu",
                Component.translatable("simplekeybinds"),
                ModConstants.LOGO,
                b -> openScreen(new MainMenuScreen(parent))
        )
                .updateSupplier(MOD_INFO::hasUpdate)
                .tooltip(Component.translatable("simplekeybinds.title"))
                .build();
    }

    /**
     * Sends the successfully initialized message.
     */
    public static void initializeSuccess() {
        ModConstants.LOGGER.info("Simple Keybinds version {} (for {}) loaded successfully!", SimpleKeybindsPlatforms.getPlatform().modVersion(), Balm.platform().name());
    }

    /**
     * Sends a client message to the player.
     */
    public static void sendClientMessage(LocalPlayer player, Component message) {
        if (player == null) {
            return;
        }

        if (client().messages.overlay()) {
            player.sendOverlayMessage(message);
        } else {
            player.sendSystemMessage(message);
        }
    }

    /**
     * Mutes the game.
     */
    public static void mute(Options options) {
        if (scrolling) {
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
    public static void unmute(Options options) {
        options.getSoundSourceOptionInstance(SoundSource.MASTER).set((cachedVolume == 0.0D ? 0.5D : cachedVolume));
        muted = false;
    }

    /**
     * @return the maximum gui scale allowed.
     */
    public static int getMaxGuiScale() {
        return !getMinecraft().isRunning()
                ? 2147483646
                : getMinecraft().getWindow().calculateScale(0, getMinecraft().isEnforceUnicode());
    }

    /**
     * Calculates the maximum gui scale.
     */
    public static int calculateMaxGuiScale(float min, float max, int scale) {
        return Math.max((int)min, Math.min((int)max, scale));
    }

    /**
     * Handles fog rendering.
     */
    public static void handleFog(Entity entity, FogType fogtype, FogData fogData) {
        if (entity instanceof LivingEntity livingEntity &&
                !livingEntity.hasEffect(MobEffects.BLINDNESS) &&
                !livingEntity.hasEffect(MobEffects.DARKNESS) &&
                !client().fog &&
                fogtype != FogType.WATER &&
                fogtype != FogType.LAVA &&
                fogtype != FogType.POWDER_SNOW) {
            fogData.renderDistanceEnd = Integer.MAX_VALUE;
            fogData.environmentalEnd = Integer.MAX_VALUE;
        }
    }
}