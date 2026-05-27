package net.dillon.simplekeybinds.mixin;

import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.callback.IncreasedBrightnessSliderCallback;
import net.dillon.simplekeybinds.helper.ModHelper;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.Function;

@Mixin(OptionInstance.class)
public abstract class OptionInstanceMixin {
    @Shadow @Final
    Component caption;
    @Shadow @Final @Mutable
    Function<Double, Component> toString;
    @Shadow @Final @Mutable
    private OptionInstance.ValueSet<Double> values;
    @Shadow @Final @Mutable
    private Codec<Double> codec;
    @Shadow
    @Final
    @Mutable
    private Consumer<Double> onValueUpdate;

    @Inject(at = @At("RETURN"), method = "<init>*", remap = false)
    protected void init(CallbackInfo info) {
        if (this.caption.getContents() instanceof TranslatableContents translatableContents && translatableContents.getKey().equals("options.gamma")) {
            this.onValueUpdate = ModHelper::onValueUpdate;
            this.toString = ModHelper::toComponentString;
            this.values = IncreasedBrightnessSliderCallback.INSTANCE;
            this.codec = this.values.codec();
        }
    }
}