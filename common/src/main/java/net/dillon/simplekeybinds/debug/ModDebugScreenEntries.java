package net.dillon.simplekeybinds.debug;

import net.dillon.dillonlib.client.component.DebugTextAlignmentBuilder;
import net.minecraft.client.gui.components.debug.DebugEntryCategory;
import net.minecraft.client.gui.components.debug.DebugGroup;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;

import static net.dillon.dillonlib.factory.ClientFactories.createDebugEntryKey;
import static net.dillon.simplekeybinds.helper.ModHelper.simpleKeybindsIdentifier;
import static net.dillon.simplekeybinds.option.OptionInstances.client;

/**
 * All mod debug entries.
 */
public class ModDebugScreenEntries {
    public static final ResourceKey<DebugScreenEntry> BRIGHTNESS = key("brightness");

    protected static final DebugGroup SIMPLE_KEYBINDS = DebugTextAlignmentBuilder
            .titled(Component.translatable("simplekeybinds"))
            .withAutoColumn(client().debugTextAlignment)
            .build();

    protected static final DebugEntryCategory MOD_CATEGORY = new DebugEntryCategory(Component.translatable("simplekeybinds"), 3.0F);

    /**
     * @return a debug entry key.
     */
    private static ResourceKey<DebugScreenEntry> key(String name) {
        return createDebugEntryKey(simpleKeybindsIdentifier(name));
    }
}