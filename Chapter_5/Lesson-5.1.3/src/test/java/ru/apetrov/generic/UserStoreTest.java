package ru.apetrov.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStoreTest {

    private SimpleArray array;
    private UserStore store;
    private Base user1;
    private Base user2;

    @Before
    public void init() {
        this.store = new UserStore(10);
        this.user1 = new User("1");
        this.user2 = new User("2");
        this.store.add(this.user1);
        this.store.add(this.user2);
        this.array = store.getArray();
    }

    @Test
    public void whenAddNewValueThenCheckValue() {
        assertThat(this.array.get(0), is(user1));
    }

    @Test
    public void whenUpdateValueThenCheckNewValue() {
        this.store.update("1", this.user2);
        assertThat(this.array.get(0), is(this.user2));
    }

    @Test
    public void whenDeleteValueThenCheckThinPositionToNull() {
        this.store.delete("1");
        assertNull(this.array.get(0));
    }
}