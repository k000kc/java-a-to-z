package ru.apetrov.Storage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 05.07.2017.
 */
@ThreadSafe
public class UserStorage implements BaseStorage {

    /**
     * map of users.
     */
    private Map<Integer, User> users;

    /**
     * Constructor.
     */
    public UserStorage() {
        this.users = new HashMap<Integer, User>();
    }

    /**
     * add.
     * @param user user.
     * @return true - if add user successfully.
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (user != null && !users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * update.
     * @param newUser new user.
     * @param oldUser old user.
     * @return true - if update user successfully.
     */
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

    /**
     * delete.
     * @param userId user id.
     * @return true - if delete user successfully.
     */
    public synchronized boolean delete(Integer userId) {
        boolean result = false;
        User user = this.users.remove(userId);
        if (user != null) {
            result = true;
        }
        return result;
    }

    /**
     * transfer an amount from first user to second user.
     * @param fromId id first user.
     * @param toId id second user.
     * @param amount amount.
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User fromUser = this.getUserById(fromId);
        User toUser = this.getUserById(toId);
        if (fromUser.getAmount() <= amount) {
            toUser.setAmount(toUser.getAmount() + fromUser.getAmount());
            fromUser.setAmount(0);
        } else {
            toUser.setAmount(toUser.getAmount() + amount);
            fromUser.setAmount(fromUser.getAmount() - amount);
        }
    }

    /**
     * get user by id.
     * @param userId user id.
     * @return user.
     */
    private User getUserById(int userId) {
        return this.users.get(userId);
    }
}
