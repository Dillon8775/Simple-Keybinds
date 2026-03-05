package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * A mixin plugin that determines whether {@code certain mixins} should be applied, if the {@code speedrunner mod is loaded.}
 */
@Environment(EnvType.CLIENT)
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Determines whether cretain mixins should be applied.
     */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean bl = this.shouldNotApply(mixinClassName);
        if (bl) {
            SimpleKeybinds.LOGGER.warn("Skipping mixin " + mixinClassName + " for target " + targetClassName + " because it should not be applied.");
        }
        return !bl;
    }

    /**
     * Returns client-side mixins that should not apply based on certain conditions.
     */
    private boolean shouldNotApply(String mixinClassName) {
        if (FabricLoader.getInstance().isModLoaded("speedrunnermod") && mixinClassName.equals("net.dillon.simplekeybinds.mixin.FogRendererMixin")) {
            return true;
        }
        return false;
    }

    // Other methods...
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}