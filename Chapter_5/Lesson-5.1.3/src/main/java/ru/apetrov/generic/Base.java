package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public abstract class Base {

    /**
     * id of model.
     */
    private String id;

    /**
     * Constructor of class.
     * @param id id.
     */
    public Base(String id) {
        this.id = id;
    }

    /**
     * Getter of id.
     * @return id id.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }
}
