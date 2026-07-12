package net.dillon.simplekeybinds.util;

import net.dillon.simplekeybinds.platform.MultiLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for Conditional mixin plugin.
 */
@SuppressWarnings("unchecked")
public class MixinPluginUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("SimpleKeybinds/Mixin");
    private static final List<PredicateEntry> ENTRIES = List.of(
            new PredicateEntry(
                    new String[]{"mixin.FogRendererMixin"},
                    MultiLoader.getPlatform().isModLoaded("qualityofqueso") || MultiLoader.getPlatform().isModLoaded("speedrunnermod"),
                    "Quality of Queso mod or Speedrunner Mod is loaded, and those mods already change fog functionality."
            )
    );

    /**
     * @return {@code false} if mixin should not apply.
     */
    public static boolean shouldNotApply(String targetClassName, String mixinClassName) {
        for (PredicateEntry entry : ENTRIES) {
            if (entry.condition()) {
                for (String s : entry.mixins()) {
                    String name = "net.dillon.simplekeybinds.mixin." + s;
                    if (name.equals(mixinClassName)) {
                        LOGGER.warn("Skipping mixin {} for class {}: {}",
                                mixinClassName,
                                targetClassName,
                                entry.reason()
                        );
                        return true;
                    }
                }
            }
        }

        return false;
    }
}