package ru.apetrov.model;

import java.sql.Timestamp;

/**
 * Created by Andrey on 05.02.2018.
 */
public class User {
    private String name;
    private String login;
    private String email;
    private Timestamp createDate;

    public User(String name, String login, String email, Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
}
