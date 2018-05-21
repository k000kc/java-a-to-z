package ru.apetrov.dao;

import ru.apetrov.models.User;

import java.util.Set;

public class UserImpl extends ModelBaseDAO<User,String> {

    @Override
    public void create(User user) {

    }

    @Override
    public User getById(String login) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(String login) {

    }
}
