package net.dillon.simplekeybinds.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon.simplekeybinds.SimpleKeybinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Environment(EnvType.CLIENT)
public class ModOptions {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static final String CONFIG = "simple-keybinds.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();

    public boolean fog = true;
    public boolean fullBright = false;

    public static void loadConfig() {
        File configFile = getConfigFile();

        if (!configFile.exists()) {
            OPTIONS = new ModOptions();
            SimpleKeybinds.LOGGER.info("Creating simple keybinds options file...");
        } else {
            readConfig();
        }
        saveConfig();
    }

    public static void readConfig() {
        OPTIONS = getConfig();
    }

    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SimpleKeybinds.options()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    public static ModOptions getConfig() {
        File file = getConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setConfig(newconfig);
            return newconfig;
        }
    }

    private static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }
}