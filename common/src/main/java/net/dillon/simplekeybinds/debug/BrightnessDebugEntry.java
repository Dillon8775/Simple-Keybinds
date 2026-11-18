package net.dillon.simplekeybinds.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

/**
 * Displays the player's brightness.
 */
public class BrightnessDebugEntry extends SimpleKeybindsDebugEntry {

    @Override
    public void display(DebugScreenDisplayer lines, @Nullable Level serverOrClientLevel, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (Minecraft.getInstance().level == null) {
            return;
        }

        lines.addFactToGroup(
                ModDebugScreenEntries.SIMPLE_KEYBINDS,
                "Brightness",
                fact -> fact.value((int)(Minecraft.getInstance().options.gamma().get() * 100) + "%")
        );
    }
}