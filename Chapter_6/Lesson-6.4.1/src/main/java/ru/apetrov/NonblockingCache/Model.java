package ru.apetrov.NonblockingCache;

/**
 * Created by Andrey on 17.08.2017.
 */
public class Model {

    /**
     * id.
     */
    private final int id;

    /**
     * name.
     */
    private final String name;

    /**
     * version.
     */
    private int version;

    /**
     * Constructor.
     * @param id id.
     * @param name name.
     */
    public Model(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    /**
     * getter.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter.
     * @return version.
     */
    public int getVersion() {
        return version;
    }

    /**
     * increment version.
     */
    public void incrementVersion() {
        this.version++;
    }
}
