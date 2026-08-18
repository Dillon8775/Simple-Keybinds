package net.dillon.simplekeybinds.debug;

import net.dillon.dillonlib.mixin.accessor.DebugScreenEntriesInvoker;
import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.network.chat.Component;

import static net.dillon.simplekeybinds.helper.ModHelper.simpleKeybindsIdentifier;

/**
 * All mod debug entries.
 */
public class ModDebugScreenEntries {
    public static final DebugEntryCategory SIMPLE_KEYBINDS = new DebugEntryCategory(Component.translatable("simplekeybinds.title"), 4.0F);

    /**
     * Registers all Simple Keybinds debug entries.
     */
    public static void registerDebugEntries() {
        DebugScreenEntriesInvoker.invokeRegister(simpleKeybindsIdentifier("brightness"), new ModDebugEntryBrightness());
    }
}