package ru.apetrov.storage;

import org.junit.Test;
import ru.apetrov.model.User;

import java.sql.Timestamp;

/**
 * Created by Andrey on 06.02.2018.
 */
public class UserStoreTest {

    @Test
    public void testConnection() {
        UserStore store = UserStore.getInstance();
        store.initConnection();
        store.put(new User("B", "C", "b@C", new Timestamp(System.currentTimeMillis())));
        store.put(new User("A", "S", "A@S", new Timestamp(System.currentTimeMillis())));
        store.put(new User("D", "F", "d@F", new Timestamp(System.currentTimeMillis())));
//        store.delete(new User("B", "C", "b@C", new Timestamp(System.currentTimeMillis())));
        store.update(new User("B", "E", "b@E", new Timestamp(System.currentTimeMillis())));
        System.out.println(store.getAll());
    }
}