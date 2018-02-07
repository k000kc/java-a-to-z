package ru.apetrov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrey on 05.02.2018.
 */
public class Settings {

    private static final Logger log = LoggerFactory.getLogger(Settings.class);
    private Properties properties;

    public Settings() {
        this.properties = new Properties();
    }

    public String getValue(String key) {
        String result = null;
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("config.properties")){
            this.properties.load(in);
            result = this.properties.getProperty(key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}