package net.dillon.simplekeybinds.callback;

import net.dillon.simplekeybinds.util.ModUtil;

public class MuteCallback {
    private static int scrollTicksRemaining = 0;
    public static int muteCooldown = 0;
    private static final int DEFAULT_COOLDOWN = 10; // 10 ticks

    /**
     * Tells the game that the user is scrolling.
     */
    public static void tickScroll() {
        ModUtil.scrolling = true;
        scrollTicksRemaining = DEFAULT_COOLDOWN;
    }

    /**
     * Tries to detect if the user isn't scrolling.
     */
    public static void unscroll() {
        if (scrollTicksRemaining > 0) {
            scrollTicksRemaining--;
        } else {
            ModUtil.scrolling = false;
        }
    }

    /**
     * Resets the mute key cooldown.
     */
    public static void resetMuteCooldown() {
        muteCooldown = DEFAULT_COOLDOWN - 4;
    }

    /**
     * Ticks the mute cooldown so you can mute the game.
     */
    public static void allowMuting() {
        if (muteCooldown > 0) {
            muteCooldown--;
        }
    }
}