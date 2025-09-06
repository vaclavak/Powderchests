package vpp.vac.chestcounter.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import vpp.vac.chestcounter.main.Main;

public class ConfigManager {
    private static final String CONFIG_DIR_NAME = "Powderchests";
    private static final String CONFIG_FILE_NAME = "config.txt";
    
    /**
     * Gets the universal config directory path inside .minecraft
     */
    private static File getConfigDirectory() {
        File minecraftDir = Minecraft.getMinecraft().mcDataDir;
        return new File(minecraftDir, CONFIG_DIR_NAME);
    }
    
    /**
     * Gets the config file
     */
    private static File getConfigFile() {
        return new File(getConfigDirectory(), CONFIG_FILE_NAME);
    }
    
    /**
     * Loads configuration from file, reverts to defaults if file doesn't exist or is corrupted
     */
    public static void loadConfig() {
        File configFile = getConfigFile();
        
        if (!configFile.exists()) {
            System.out.println(Main.PREFIX + "Config file not found, using defaults");
            return;
        }
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(configFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    continue;
                }
                
                String key = parts[0].trim();
                String value = parts[1].trim();
                
                switch (key) {
                    case "displayEnabled":
                        Main.displayEnabled = Boolean.parseBoolean(value);
                        break;
                    case "displayX":
                        try {
                            Main.displayX = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            System.out.println(Main.PREFIX + "Invalid displayX value in config, using default");
                        }
                        break;
                    case "displayY":
                        try {
                            Main.displayY = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            System.out.println(Main.PREFIX + "Invalid displayY value in config, using default");
                        }
                        break;
                }
            }
            System.out.println(Main.PREFIX + "Configuration loaded from " + configFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(Main.PREFIX + "Error loading config file: " + e.getMessage() + ", using defaults");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
        }
    }
    
    /**
     * Saves current configuration to file
     */
    public static void saveConfig() {
        File configDir = getConfigDirectory();
        
        // Create directory if it doesn't exist
        if (!configDir.exists()) {
            if (!configDir.mkdirs()) {
                System.out.println(Main.PREFIX + "Error: Could not create config directory: " + configDir.getAbsolutePath());
                return;
            }
        }
        
        File configFile = getConfigFile();
        
        FileWriter writer = null;
        try {
            writer = new FileWriter(configFile);
            writer.write("# Powderchests Configuration File\n");
            writer.write("# This file is automatically managed by the mod\n");
            writer.write("\n");
            writer.write("displayEnabled=" + Main.displayEnabled + "\n");
            writer.write("displayX=" + Main.displayX + "\n");
            writer.write("displayY=" + Main.displayY + "\n");
            
            System.out.println(Main.PREFIX + "Configuration saved to " + configFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(Main.PREFIX + "Error saving config file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // Ignore close errors
                }
            }
        }
    }
}