package main.java.ru.apetrov.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Andrey on 17.12.2016.
 */
public class Settings {

    private Properties properties = new Properties();

    public void load() {
        ClassLoader loader = Settings.class.getClassLoader();
        try(InputStream it = loader.getResourceAsStream("config.properties")) {
            this.properties.load(it);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}
