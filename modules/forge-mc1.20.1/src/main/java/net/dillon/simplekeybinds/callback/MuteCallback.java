package net.dillon.simplekeybinds.callback;

import net.dillon.simplekeybinds.SimpleKeybinds;

public class MuteCallback {
    private static int scrollTicksRemaining = 0;
    public static int muteCooldown = 0;
    private static final int DEFAULT_COOLDOWN = 10; // 10 ticks

    /**
     * Tells the game that the user is scrolling.
     */
    public static void tickScroll() {
        SimpleKeybinds.scrolling = true;
        scrollTicksRemaining = DEFAULT_COOLDOWN;
    }

    /**
     * Ticks the scroll time.
     */
    public static void unscroll() {
        if (scrollTicksRemaining > 0) {
            scrollTicksRemaining--;
        } else {
            SimpleKeybinds.scrolling = false;
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