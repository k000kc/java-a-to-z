package ru.apetrov.Storage;

import java.util.*;

/**
 * Created by Andrey on 05.07.2017.
 */
public class UserStorage implements BaseStorage{

    private Map<Integer, User> users;

    public UserStorage() {
        this.users = new HashMap<Integer, User>();
    }

    public boolean add(User user) {
        boolean result = false;
        if (user != null && !users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User newUser, User oldUser) {
        boolean result = false;
        if (newUser != null) {
            for (Map.Entry currentUser : this.users.entrySet()) {
                if (currentUser.getKey().equals(newUser.getId())) {
                    this.users.put((Integer) currentUser.getKey(), newUser);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public synchronized boolean delete(Integer userId) {
        boolean result = false;
        User user = this.users.remove(userId);
        if (user != null) {
            result = true;
        }
        return result;
    }
}
