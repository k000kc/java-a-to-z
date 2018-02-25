package ru.apetrov.storege;

import org.junit.Test;
import ru.apetrov.model.User;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by Andrey on 21.02.2018.
 */
public class UserStoreTest {


    /**
     * tests db.
     */
    @Test
    public void testConnection() {
        UserStore store = UserStore.getInstance();

        store.put(new User("123", "Andrey", "fgdl@mail.ru", new Timestamp(System.currentTimeMillis())));

    }

}