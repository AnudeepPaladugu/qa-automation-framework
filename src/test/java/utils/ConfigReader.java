package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String CONFIG_PATH = "config/config.properties";
    private static Properties properties;

    private ConfigReader() {
    }

    public static void loadProperties() {
        properties = new Properties();

        try (InputStream inputStream =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(CONFIG_PATH)) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Configuration file not found: " + CONFIG_PATH
                );
            }

            properties.load(inputStream);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load configuration: " + CONFIG_PATH,
                    e
            );
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }

        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Required configuration property is missing: " + key
            );
        }

        return value.trim();
    }
}
