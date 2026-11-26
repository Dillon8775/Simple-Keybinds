package net.dillon.simplekeybinds.option;

import net.dillon.dillonlib.client.util.DebugTextAlignment;
import net.dillon.dillonlib.util.BaseOptions;

public class ModClientOptions {
    public static final ModClientOptionsHandler INSTANCE = new ModClientOptionsHandler();
    public boolean fog = false;
    public boolean autoBrightness = false;
    public DebugTextAlignment debugTextAlignment = DebugTextAlignment.AUTO;

    public Messages messages = Messages.OVERLAY;

    public static class ModClientOptionsHandler extends BaseOptions<ModClientOptions> {

        public ModClientOptionsHandler() {
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