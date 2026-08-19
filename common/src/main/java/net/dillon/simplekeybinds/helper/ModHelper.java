package net.dillon.simplekeybinds.helper;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.simplekeybinds.platform.SimpleKeybindsPlatforms;
import net.dillon.simplekeybinds.screen.MainMenuScreen;
import net.minecraft.client.Minecraft;
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

import java.util.Map;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.simplekeybinds.helper.ModConstants.MOD_ID;
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
    public static UpdatableSpriteButton menuButton(Screen parent) {
        return ClientTasks.createMenuButton(
                "Simple Keybinds Main Menu",
                ModConstants.LOGO,
                (button) -> openScreen(new MainMenuScreen(parent)),
                Map.of(
                        ModConstants.HAS_UPDATE,
                        Component.translatable("simplekeybinds.gui.update_available")
                ),
                Component.translatable("simplekeybinds.menu.title"),
                true);
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

    public static Component toComponentString(Double gamma) {
        return Component.translatable("options.gamma").append(": ").append(Component.literal(Math.round(gamma * 100) + "%"));
    }

    public static void onValueUpdate(Double brightness) {
        Minecraft.getInstance().options.gamma().set(brightness);
    }
}