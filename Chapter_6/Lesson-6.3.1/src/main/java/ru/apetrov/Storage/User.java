package ru.apetrov.Storage;

/**
 * Created by Andrey on 05.07.2017.
 */
public class User {

    private String name;
    private int id;
    private int amount;

    public User(String name, int id, int amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
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
