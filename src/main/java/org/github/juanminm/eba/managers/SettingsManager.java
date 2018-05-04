package org.github.juanminm.eba.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class SettingsManager {
    public static final String PROXY_ENABLED = "proxy.enabled";
    public static final String PROXY_HOST=  "proxy.host";
    public static final String PROXY_PASSWORD = "proxy.password";
    public static final String PROXY_PORT = "proxy.port";
    public static final String PROXY_USERNAME = "proxy.username";
    public static final String PROXY_WITH_AUTH = "proxy.with_auth";

    private static final Logger LOGGER = Logger
            .getLogger(SettingsManager.class.getName());
    private static SettingsManager settingsManager;
    
    public static SettingsManager getInstance() {
        if (settingsManager == null)
            settingsManager = new SettingsManager();

        return settingsManager;
    }

    private File configFile;
    private final String FILENAME = "eba.properties";
    private Properties properties = new Properties();

    private SettingsManager() {
        configFile = new File(FILENAME);

        if (!configFile.exists())
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                LOGGER.severe("File could not be created: " + e.getMessage());
            }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, Object defaultValue) {
        return properties.getProperty(key, String.valueOf(defaultValue));
    }

    public void loadSettings() throws IOException {
        FileInputStream fis = new FileInputStream(configFile);

        properties.load(fis);
        fis.close();
    }

    public void removeProperty(Object key) {
        properties.remove(key);
    }

    public void saveSettings() throws IOException {
        FileOutputStream fos = new FileOutputStream(configFile);

        properties.store(fos, null);
        fos.close();
    }
    
    public void setProperty(String key, Object value) {
        properties.setProperty(key, String.valueOf(value));
    }

}
