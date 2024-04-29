package net.dillon.simplekeybinds.mixin;

import net.dillon.simplekeybinds.SimpleKeybinds;
import net.dillon.simplekeybinds.keybinds.ModKeybinds;
import net.dillon.simplekeybinds.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final
    public InGameHud inGameHud;

    @Inject(at = @At("TAIL"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        while (ModKeybinds.fogKey.wasPressed()) {
            SimpleKeybinds.options().fog = !SimpleKeybinds.options().fog;
            ModOptions.saveConfig();
            MinecraftClient.getInstance().worldRenderer.reload();
            debugWarn(SimpleKeybinds.options().fog ? "simplekeybinds.toggle_fog.on" : "simplekeybinds.toggle_fog.off");
        }

        while (ModKeybinds.fullbrightKey.wasPressed()) {
            SimpleKeybinds.options().fullBright = !SimpleKeybinds.options().fullBright;
            ModOptions.saveConfig();
            MinecraftClient.getInstance().options.gamma = SimpleKeybinds.options().fullBright ? SimpleKeybinds.maxBrightness : 1.0D;
            debugWarn(SimpleKeybinds.options().fullBright ? "simplekeybinds.toggle_fullbright.on" : "simplekeybinds.toggle_fullbright.off");
        }

        while (ModKeybinds.hitboxesKey.wasPressed()) {
            boolean bl = !MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes();
            MinecraftClient.getInstance().getEntityRenderDispatcher().setRenderHitboxes(bl);
            debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
        }

        while (ModKeybinds.chunkBordersKey.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
            debugWarn(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
        }
    }

    @Unique
    private void debugWarn(String key, Object... args) {
        this.inGameHud.getChatHud().addMessage((new LiteralText("")).append((new TranslatableText("debug.prefix")).formatted(Formatting.YELLOW, Formatting.BOLD)).append(" ").append(new TranslatableText(key, args)));
    }
}