package net.dillon.simplekeybinds.debug;

import net.dillon.simplekeybinds.mixin.DebugScreenEntriesAccessor;
import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.network.chat.Component;

import static net.dillon.simplekeybinds.util.ModUtil.ofSimpleKeybinds;

/**
 * All debug entries.
 */
public class ModHudEntries {
    public static final DebugEntryCategory SIMPLE_KEYBINDS = new DebugEntryCategory(Component.translatable("simplekeybinds.keybinds"), 4.0F);

    /**
     * Registers all Simple Keybinds debug entries.
     */
    public static void registerDebugEntries() {
        DebugScreenEntriesAccessor.invokeRegister(ofSimpleKeybinds("brightness"), new BrightnessHudEntry());
    }
}