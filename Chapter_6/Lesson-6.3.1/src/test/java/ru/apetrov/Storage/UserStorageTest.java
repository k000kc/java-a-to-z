package ru.apetrov.Storage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 05.07.2017.
 */
public class UserStorageTest {

    /**
     * storage.
     */
    BaseStorage storage = new UserStorage();

    /**
     * check add method.
     */
    @Test
    public void whenAddNewUserThenMethdGetTrue() {
        assertThat(this.storage.add(new User("Bob", 1, 1500)), is(true));
    }

    /**
     * check update method.
     */
    @Test
    public void whenUpdateThenCheckNewUser() {
        User user = new User("Mike", 1, 1000);
        this.storage.add(user);
        assertThat(this.storage.update(new User("Rob", 1, 1500), user), is(true));
    }

    /**
     * check delete method.
     */
    @Test
    public void whenRemoveUserByIdThenCheckResult() {
        User user = new User("Mike", 1, 1000);
        this.storage.add(user);
        assertThat(true, is(this.storage.delete(1)));
    }

    /**
     * check transfer method.
     */
    @Test
    public void whenFromUserTransferAmountToUserThenCheckResult() {
        User user1 = new User("A", 1, 1000);
        User user2 = new User("B", 2, 500);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(1, 2, 100);
        assertThat(user1.getAmount(), is(900));
        assertThat(user2.getAmount(), is(600));
    }
}