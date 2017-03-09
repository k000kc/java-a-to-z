package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public abstract class Base {

    private String id;

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
