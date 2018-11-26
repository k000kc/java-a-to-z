package ru.apetrov.generic;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Constructor of user dao.
     * @param size size dao.
     */
    protected UserStore(int size) {
        super(size);
    }
}
