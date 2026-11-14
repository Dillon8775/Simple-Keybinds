package net.dillon.simplekeybinds.helper;

import net.dillon.dillonlib.platform.info.ModInfo;
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
    public static final ModInfo MOD_INFO = ModInfo.create(
            MOD_ID,
            "simple-keybinds",
            Component.translatable("simplekeybinds")
    );
    public static final Logger LOGGER = LoggerFactory.getLogger("Simple Keybinds");
    public static final Identifier LOGO = simpleKeybindsIdentifier("simplekeybinds");
}