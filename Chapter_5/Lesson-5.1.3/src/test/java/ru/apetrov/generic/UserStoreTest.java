package ru.apetrov.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStoreTest {

    /**
     * simple array.
     */
    private SimpleArray array;

    /**
     * user dao.
     */
    private UserStore store;

    /**
     * user1.
     */
    private User user1;

    /**
     * user2.
     */
    private User user2;

    /**
     * init.
     */
    @Before
    public void init() {
        this.store = new UserStore(10);
        this.user1 = new User("1");
        this.user2 = new User("2");
        this.store.add(this.user1);
        this.store.add(this.user2);
        this.array = store.getArray();
    }

    /**
     * check mathod get().
     */
    @Test
    public void whenAddNewValueThenCheckValue() {
        assertThat(this.array.get(0), is(user1));
    }

    /**
     * Check method update.
     */
    @Test
    public void whenUpdateValueThenCheckNewValue() {
        this.store.update("1", this.user2);
        assertThat(this.array.get(0), is(this.user2));
    }

    /**
     * Check method delete.
     */
    @Test
    public void whenDeleteValueThenCheckThinPositionToNull() {
        this.store.delete("1");
        assertNull(this.array.get(0));
    }

    /**
     * error.
     */
    @Test
    public void whenErrorTest() {
        SimpleArray simpleArray = new SimpleArray(10);
        UserStore userStore = new UserStore(10);
        User userTest = new User("test1");
        userStore.add(userTest);
        simpleArray = userStore.getArray();
        assertThat(simpleArray.get(0), is(userTest));
    }
}