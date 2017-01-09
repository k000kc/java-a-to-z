package ru.apetrov.Calculator;

/**
 * Created by Andrey on 05.01.2017.
 */
public abstract class BaseActions {

    /**
     * Имя.
     */
    private String name;

    /**
     * Ключ.
     */
    private String key;

    /**
     * Конструктор.
     * @param key ключ.
     * @param name имя.
     */
    public BaseActions(String key, String name) {
        this.name = name;
        this.key = key;
    }

    /**
     * Геттер.
     * @return ключ.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Информация о действии.
     * @return информация.
     */
    public String info() {
        return String.format("Введите %s для %s", this.key, this.name);
    }

    /**
     * Реализация.
     * @param calculator класс.
     * @param input ввод.
     */
    public abstract void execute(TrigAction calculator, Input input);
}
