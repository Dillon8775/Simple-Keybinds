package net.dillon.simplekeybinds.option;

import java.util.function.Consumer;

/**
 * Getters for all Simple Keybind's option instances.
 */
public class OptionInstances {

    /**
     * Returns the options.
     */
    public static ModClientOptions client() {
        return ModClientOptions.INSTANCE.getInstance();
    }

    /**
     * Updates the client option instance.
     */
    public static void updateClient(Consumer<ModClientOptions> client) {
        ModClientOptions.INSTANCE.update(client);
    }
}