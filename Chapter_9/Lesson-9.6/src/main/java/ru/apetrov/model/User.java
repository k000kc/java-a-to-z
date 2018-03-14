package ru.apetrov.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * user.
 */
public class User {

    /**
     * User name.
     */
    private String name;

    /**
     * User login.
     */
    private String login;

    private String password;

    /**
     * User E-mail.
     */
    private String email;

    /**
     * User create date.
     */
    private Timestamp createDate;

    private String role;

    /**
     * Constructor.
     * @param login      login
     * @param name       name
     * @param email      e-mail
     * @param createDate create date.
     */
    public User(String login, String password, String name, String email, Timestamp createDate, String role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    /**
     * Getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter.
     * @return password.
     */
    public String getPassword() {
        return String.valueOf(this.hashCode());
    }

    /**
     * Getter.
     * @return e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter.
     * @return create date.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("User: login - %s, name - %s, email - %s, create date - %s", login, name, email, createDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
