package net.dillon.simplekeybinds.platform;

public interface IPlatformHelper {

    /**
     * @return the mod version.
     */
    default String getModVersion() {
        return "1.2.9";
    }

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);
}