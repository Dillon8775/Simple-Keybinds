package net.dillon.simplekeybinds.mixin;

import com.mojang.serialization.Codec;
import net.dillon.simplekeybinds.callback.IncreasedBrightnessSliderCallback;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
@Mixin(SimpleOption.class)
public class SimpleOptionMixin {
    @Shadow @Final
    Text text;
    @Shadow @Final @Mutable
    Function<Double, Text> textGetter;
    @Shadow @Final @Mutable
    private SimpleOption.Callbacks<Double> callbacks;
    @Shadow @Final @Mutable
    private Codec<Double> codec;
    @Shadow @Final @Mutable
    private Consumer<Double> changeCallback;

    @Inject(at = @At("RETURN"), method = "<init>*")
    private void init(CallbackInfo info) throws Exception {
        TextContent content = this.text.getContent();
        if (!(content instanceof TranslatableTextContent))
            return;

        String key = ((TranslatableTextContent) content).getKey();
        if (!key.equals("options.gamma"))
            return;

        this.textGetter = this::textGetter;
        this.callbacks = IncreasedBrightnessSliderCallback.INSTANCE;
        this.codec = this.callbacks.codec();
        this.changeCallback = this::changeCallback;
    }

    @Unique
    private Text textGetter(Double gamma) {
        long brightness = Math.round(gamma * 100);
        return Text.translatable("options.gamma").append(": ").append(brightness == 0 ? Text.translatable("options.gamma.min") : brightness == 100 ? Text.translatable("options.gamma.max") : Text.literal(String.valueOf(brightness)));
    }

    @Unique
    private void changeCallback(Double gamma) {
        MinecraftClient.getInstance().options.getGamma().setValue(gamma);
    }
}