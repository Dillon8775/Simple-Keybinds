package net.dillon.simplekeybinds.util;

import net.dillon.simplekeybinds.platform.MultiLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for Conditional mixin plugin.
 */
@SuppressWarnings("unchecked")
public class MixinPluginUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("SimpleKeybinds/Mixin");
    private static String REASON = "";

    /**
     * @return {@code false} if mixin should not apply.
     */
    private static boolean shouldApply(String mixinClassName) {
        if (mixinClassName.equals("net.dillon.simplekeybinds.mixin.FogRendererMixin")) {
            String qoq = "qualityofqueso";
            String speedrunnerMod = "speedrunnermod";
            if (MultiLoader.getPlatform().isModLoaded(qoq) || MultiLoader.getPlatform().isModLoaded(speedrunnerMod)) {
                LOGGER.warn("Mod Quality of Queso or Speedrunner Mod is loaded, not applying Simple Keybind's fog functionality.");
                REASON = "Quality of Queso or Speedrunner Mod is loaded, and those mods already change fog functionality.";
                return false;
            }
        }

        // Always apply other mixins
        return true;
    }

    /**
     * @return {@code true} if a mixin should be applied to the game.
     */
    public static boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean bl = shouldApply(mixinClassName);
        if (!bl) {
            LOGGER.warn("Skipping mixin {} for target {} because it should not be applied. Reason: {}",
                    mixinClassName,
                    targetClassName,
                    REASON.isEmpty() ? "null" : REASON);
        }
        return bl;
    }
}