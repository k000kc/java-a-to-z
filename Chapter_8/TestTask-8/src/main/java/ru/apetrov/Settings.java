package ru.apetrov;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrey on 23.12.2017.
 */
public class Settings {

    private Properties properties;

    public Settings() {
        this.properties = new Properties();
    }

    /**
     * Достаем значение из файла config.properties по ключу key.
     * @param key ключ.
     * @return значение.
     */
    public String getValue(String key) {
        String result = null;
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("config.properties")) {
            properties.load(in);
            result = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Изменение значения newValue, по ключу key.
     * @param key ключ.
     * @param newValue значение.
     */
    public void savePropertiesForSecondStart(String key, String newValue) {
        try (FileWriter out = new FileWriter("Chapter_8\\TestTask-8\\src\\main\\resources\\config.properties")) {
            this.properties.setProperty(key, newValue);
            this.properties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
