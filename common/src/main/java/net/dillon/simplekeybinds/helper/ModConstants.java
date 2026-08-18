package net.dillon.simplekeybinds.helper;

import net.dillon.dillonlib.util.UpdateChecker;
import net.dillon.simplekeybinds.platform.SimpleKeybindsPlatforms;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.dillon.simplekeybinds.helper.ModHelper.simpleKeybindsIdentifier;

/**
 * Constant values for Simple Keybinds.
 */
public class ModConstants {
    public static final String MOD_ID = "simplekeybinds";
    public static final Component VERSION = Component.literal(SimpleKeybindsPlatforms.getPlatform().modVersion());
    public static final Logger LOGGER = LoggerFactory.getLogger("Simple Keybinds");
    public static final boolean HAS_UPDATE = UpdateChecker.hasUpdate(UpdateChecker.checkForUpdate(
            "simple-keybinds",
            SimpleKeybindsPlatforms.getPlatform().modVersion()
    ));
    public static final Identifier LOGO = simpleKeybindsIdentifier("simplekeybinds");
}