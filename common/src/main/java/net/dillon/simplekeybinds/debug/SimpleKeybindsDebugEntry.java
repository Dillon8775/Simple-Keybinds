package net.dillon.simplekeybinds.debug;

import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;

import javax.annotation.Nonnull;

/**
 * An abstract {@link DebugScreenEntry}.
 */
public abstract class SimpleKeybindsDebugEntry implements DebugScreenEntry {

    /**
     * @return these debug entries should always be allowed.
     */
    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }

    /**
     * @return always add this debug entry to the mod's category.
     */
    @Override
    public @Nonnull DebugEntryCategory category() {
        return ModDebugScreenEntries.MOD_CATEGORY;
    }
}