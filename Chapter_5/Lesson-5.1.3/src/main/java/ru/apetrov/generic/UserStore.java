package ru.apetrov.generic;

import ru.apetrov.generic.Store;
import ru.apetrov.generic.User;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStore extends AbstractStore<User> {

    protected UserStore(int size) {
        super(size);
    }
}
