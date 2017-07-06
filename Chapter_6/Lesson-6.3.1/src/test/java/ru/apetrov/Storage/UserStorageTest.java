package ru.apetrov.Storage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 05.07.2017.
 */
public class UserStorageTest {

    BaseStorage storage = new UserStorage();

    @Test
    public void whenAddNewUserThenMethdGetTrue() {
        assertThat(this.storage.add(new User("Bob", 1, 1500)), is(true));
    }

    @Test
    public void whenUpdateThenCheckNewUser() {
        User user = new User("Mike", 1, 1000);
        this.storage.add(user);
        assertThat(this.storage.update(new User("Rob", 1, 1500), user), is(true));

    }

    @Test
    public void whenRemoveUserByIdThenCheckResult() {
        User user = new User("Mike", 1, 1000);
        this.storage.add(user);
        assertThat(true, is(this.storage.delete(1)));
    }
}