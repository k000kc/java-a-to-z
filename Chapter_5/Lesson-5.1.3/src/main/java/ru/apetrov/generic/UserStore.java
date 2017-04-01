package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Constructor of user store.
     * @param size size store.
     */
    protected UserStore(int size) {
        super(size);
    }
}
