package main.java.ru.apetrov.FileManager.Server;

/**
 * Created by Andrey on 03.12.2016.
 * Срисовал с треккера. Абстрактный класс для апи Сервера.
 */
public abstract class BaseAction {

    /**
     * Название апи.
     */
    private String name;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BaseAction(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Выполнение апи.
     */
    public abstract String execute(String command);
}
