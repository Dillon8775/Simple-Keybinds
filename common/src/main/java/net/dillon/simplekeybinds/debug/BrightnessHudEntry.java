package net.dillon.simplekeybinds.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

/**
 * Displays the player's brightness.
 */
public class BrightnessHudEntry implements DebugScreenEntry {

    @Override
    public void display(DebugScreenDisplayer lines, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (Minecraft.getInstance().level == null) {
            return;
        }

        lines.addLine("Brightness: " + (int)(Minecraft.getInstance().options.gamma().get() * 100) + "%");
    }

    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }

    /**
     * @return Always add the Quality of Queso debug entry to the mod's category.
     */
    @Override
    public DebugEntryCategory category() {
        return ModHudEntries.SIMPLE_KEYBINDS;
    }
}