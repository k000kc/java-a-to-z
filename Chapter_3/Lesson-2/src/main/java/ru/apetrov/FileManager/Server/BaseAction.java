package main.java.ru.apetrov.FileManager.Server;

/**
 * Created by Andrey on 03.12.2016.
 * Срисовал с треккера. Абстрактный класс для апи Сервера.
 */
public abstract class BaseAction {

    /**
     * Название апи.
     */
    String name;

    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * Ключ апи.
     * @return номер апи
     */
    public abstract int key();

    /**
     * Выполнение апи.
     */
    public abstract void execute();

    /**
     * Информация о конкретном апи.
     * @return информаця об апи
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

}
