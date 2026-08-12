package net.dillon.simplekeybinds.option;

import net.dillon.dillonlib.util.BaseOptions;

public class ModClientOptions {
    public static ModOptionsHandler INSTANCE = new ModOptionsHandler();
    public boolean fog = false;
    public boolean autoBrightness = false;
    public Messages messages = Messages.ACTIONBAR;

    public static class ModOptionsHandler extends BaseOptions<ModClientOptions> {

        public ModOptionsHandler() {
            super("simplekeybinds.json");
        }

        @Override
        protected ModClientOptions createDefault() {
            return new ModClientOptions();
        }

        @Override
        protected Class<ModClientOptions> getConfigClass() {
            return ModClientOptions.class;
        }
    }
}