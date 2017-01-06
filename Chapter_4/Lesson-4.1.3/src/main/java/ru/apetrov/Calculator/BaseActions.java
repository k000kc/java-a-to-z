package ru.apetrov.Calculator;

/**
 * Created by Andrey on 05.01.2017.
 */
public abstract class BaseActions {

    private String name;
    private String key;

    public BaseActions(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public String info() {
        return String.format("Введите %s для %s", this.key, this.name);
    }

    public abstract void execute(Calculator calculator, Input input);
}
