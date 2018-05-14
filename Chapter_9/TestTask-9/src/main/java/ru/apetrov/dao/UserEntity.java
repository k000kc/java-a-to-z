package ru.apetrov.dao;

import ru.apetrov.entity.User;

import java.util.List;

public class UserEntity implements EntityDAO<Long, User> {

    @Override
    public void create(User value) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void remove(Long id) {

    }
}
