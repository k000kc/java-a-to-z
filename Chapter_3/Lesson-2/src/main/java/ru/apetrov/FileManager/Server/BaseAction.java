package main.java.ru.apetrov.FileManager.Server;

import java.io.IOException;

/**
 * Created by Andrey on 03.12.2016.
 * Абстрактный класс для апи Сервера.
 */
public abstract class BaseAction {

    /**
     * Имя апи.
     */
    private String name;

    /**
     * ключ апи.
     */
    private String key;

    /**
     * для доступа к имени апи.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * для выбора апи.
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Конструктор.
     * @param key ключ апи.
     * @param name имя апи.
     */
    public BaseAction(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Выполнение апи.
     * @param command Команда.
     * @throws IOException IOException
     */
    public abstract void execute(String command) throws IOException;
}
