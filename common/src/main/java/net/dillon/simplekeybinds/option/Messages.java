package net.dillon.simplekeybinds.option;

import com.mojang.serialization.Codec;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum Messages implements StringRepresentable {
    ACTIONBAR(0, "actionbar", "simplekeybinds.options.messages.actionbar"),
    CHAT(1, "chat", "simplekeybinds.options.messages.chat");

    private static final Messages[] VALUES = Arrays.stream(Messages.values()).sorted(Comparator.comparingInt(Messages::getId)).toArray(Messages[]::new);
    public static final Codec<Messages> CODEC = StringRepresentable.fromEnum(Messages::values);
    private final int id;
    private final String name;
    private final Component translationKey;

    Messages(final int id, final String name, final String translationKey) {
        this.id = id;
        this.name = name;
        this.translationKey = Component.translatable(translationKey);
    }

    public boolean actionbar() {
        return this == ACTIONBAR;
    }

    /**
     * Returns the {@code translation key} of the {@code Creature Spawning Rate} option.
     */
    public Component getText() {
        return this.translationKey;
    }

    /**
     * Returns the {@code id value} of the {@code Messages} option.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Not sure what this does to be honest, but it's used in ModListOptions.
     */
    public static Messages byId(int id) {
        return VALUES[Mth.positiveModulo(id, VALUES.length)];
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}