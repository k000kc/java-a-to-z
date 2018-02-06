package ru.apetrov.storage;

import org.junit.Test;
import ru.apetrov.model.User;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by Andrey on 06.02.2018.
 */
public class UserStoreTest {

    @Test
    public void testConnection() {
        UserStore store = UserStore.getInstance();
        store.put(new User("A", "B", "a@B", new Timestamp(System.currentTimeMillis())));
    }
}