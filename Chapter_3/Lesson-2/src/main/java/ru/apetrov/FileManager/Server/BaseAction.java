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
     * @param k ключ апи.
     * @param n имя апи.
     */
    public BaseAction(String k, String n) {
        this.key = k;
        this.name = n;
    }

    /**
     * Выполнение апи.
     * @param command Команда.
     * @throws IOException IOException
     */
    public abstract void execute(String command) throws IOException;
}
