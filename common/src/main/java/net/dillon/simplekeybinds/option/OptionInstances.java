package net.dillon.simplekeybinds.option;

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
}