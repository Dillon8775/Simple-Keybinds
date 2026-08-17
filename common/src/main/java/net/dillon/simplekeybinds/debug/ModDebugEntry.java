package net.dillon.simplekeybinds.debug;

import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;

/**
 * An abstract Simple Keybinds {@link DebugScreenEntry}.
 */
public abstract class ModDebugEntry implements DebugScreenEntry {

    /**
     * @return Simple Keybinds debug entries should always be allowed.
     */
    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }

    /**
     * @return always add the Simple Keybinds debug entry to the mod's category.
     */
    @Override
    public DebugEntryCategory category() {
        return ModDebugScreenEntries.SIMPLE_KEYBINDS;
    }
}