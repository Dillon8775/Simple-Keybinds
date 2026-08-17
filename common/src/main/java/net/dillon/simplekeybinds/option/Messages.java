package net.dillon.simplekeybinds.option;

import net.minecraft.util.StringRepresentable;

public enum Messages implements StringRepresentable {
    OVERLAY("Overlay"),
    CHAT("Chat");

    private final String name;

    Messages(final String name) {
        this.name = name;
    }

    public boolean overlay() {
        return this == OVERLAY;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}