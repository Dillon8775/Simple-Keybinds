package net.dillon.simplekeybinds.util;

import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import net.dillon.simplekeybinds.platform.ModReferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleKeybindsMixinPlugin extends MixinPluginUtil {

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("SimpleKeybinds/Mixin");
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.simplekeybinds.mixin.";
    }

    @Override
    public List<PredicateEntry> entries() {
        return List.of(
                new PredicateEntry(
                        new String[]{"FogRendererMixin"},
                        ModReferences.isModLoaded(ModReferences.QUALITY_OF_QUESO) || ModReferences.isModLoaded(ModReferences.SPEEDRUNNER_MOD),
                        "Quality of Queso mod or Speedrunner Mod is loaded, and those mods already change fog functionality."
                )
        );
    }
}