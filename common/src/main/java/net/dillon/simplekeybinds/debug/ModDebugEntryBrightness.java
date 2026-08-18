package net.dillon.simplekeybinds.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

/**
 * Displays the player's brightness.
 */
public class ModDebugEntryBrightness extends ModDebugEntry {

    @Override
    public void display(DebugScreenDisplayer lines, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (Minecraft.getInstance().level == null) {
            return;
        }

        lines.addLine("Brightness: " + (int)(Minecraft.getInstance().options.gamma().get() * 100) + "%");
    }
}