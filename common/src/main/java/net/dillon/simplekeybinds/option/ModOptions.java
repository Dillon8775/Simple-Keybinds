package net.dillon.simplekeybinds.option;

import net.dillon.dillonlib.util.BaseOptions;

public class ModOptions {
    public static ModOptionsHandler INSTANCE = new ModOptionsHandler();
    public boolean fog = false;
    public boolean autoBrightness = false;
    public Messages messages = Messages.ACTIONBAR;

    public static class ModOptionsHandler extends BaseOptions<ModOptions> {

        public ModOptionsHandler() {
            super("simplekeybinds.json");
        }

        @Override
        protected ModOptions createDefault() {
            return new ModOptions();
        }

        @Override
        protected Class<ModOptions> getConfigClass() {
            return ModOptions.class;
        }
    }
}