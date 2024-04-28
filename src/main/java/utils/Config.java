package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(".idea/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getKey() {
        return properties.getProperty("trello.key");
    }

    public static String getToken() {
        return properties.getProperty("trello.token");
    }
}