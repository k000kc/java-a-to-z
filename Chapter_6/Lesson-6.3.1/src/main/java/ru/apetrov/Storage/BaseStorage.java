package ru.apetrov.Storage;

/**
 * Created by Andrey on 05.07.2017.
 */
public interface BaseStorage {

    boolean add(User user);
    boolean update(User newUser, User oldUser);
    boolean delete(Integer userId);
}
