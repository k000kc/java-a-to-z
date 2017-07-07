package ru.apetrov.Storage;

/**
 * Created by Andrey on 05.07.2017.
 */
public interface BaseStorage {

    /**
     * add user.
     * @param user user.
     * @return true - if add user successfully.
     */
    boolean add(User user);

    /**
     * update user.
     * @param newUser new user.
     * @param oldUser old user.
     * @return true - if update user successfully.
     */
    boolean update(User newUser, User oldUser);

    /**
     * delete user.
     * @param userId user id.
     * @return true - if delete user successfully.
     */
    boolean delete(Integer userId);

    /**
     * transfer an amount from first user to second user.
     * @param fromId id first user.
     * @param toId id second user.
     * @param amount amount.
     */
    void transfer(int fromId, int toId, int amount);
}
