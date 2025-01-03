package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.core.SimpleKeybindsCore;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * A mixin plugin that determines whether {@code certain mixins} should be applied, if the {@code speedrunner mod is loaded.}
 */
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Determines whether the {@code BackgroundRendererMixin} should be applied.
     */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (SimpleKeybindsCore.isSpeedrunnerModLoaded()) {
            return !mixinClassName.equals("net.dillon.simplekeybinds.mixin.BackgroundRendererMixin") && !mixinClassName.equals("net.dillon.simplekeybinds.mixin.SimpleOptionMixin");
        }
        return true;
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