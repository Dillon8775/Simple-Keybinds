package net.dillon.simplekeybinds.option;

import java.util.function.Consumer;

import static net.dillon.dillonlib.util.BaseOptions.guardedInstance;

/**
 * Getters for all Simple Keybind's option instances.
 */
public class OptionInstances {

    /**
     * Returns the options.
     */
    public static ModClientOptions client() {
        return guardedInstance(ModClientOptions.INSTANCE.get());
    }

    /**
     * Updates the client option instance.
     */
    public static void updateClient(Consumer<ModClientOptions> client) {
        ModClientOptions.INSTANCE.update(client);
    }
}