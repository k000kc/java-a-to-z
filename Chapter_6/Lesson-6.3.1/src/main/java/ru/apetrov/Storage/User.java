package ru.apetrov.Storage;

/**
 * Created by Andrey on 05.07.2017.
 */
public class User {

    /**
     * name.
     */
    private String name;

    /**
     * id.
     */
    private int id;

    /**
     * amount.
     */
    private int amount;

    /**
     * Constructor.
     * @param name name.
     * @param id id.
     * @param amount amount.
     */
    public User(String name, int id, int amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
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
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * amount.
     * @return amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter.
     * @param amount amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
