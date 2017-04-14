package ru.apetrov.user;

import java.util.Calendar;

/**
 * Created by Andrey on 10.04.2017.
 */
public class User {

    /**
     * user name.
     */
    private final String name;

    /**
     * amount of children.
     */
    private final int children;

    /**
     * birthday of user.
     */
    private final Calendar birthday;

    /**
     * Constructor of class.
     * @param name user name.
     * @param children amount of children.
     * @param birthday birthday of user.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
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
     * @return amount of children.
     */
    public int getChildren() {
        return children;
    }

    /**
     * getter.
     * @return birthday of user.
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }
}
