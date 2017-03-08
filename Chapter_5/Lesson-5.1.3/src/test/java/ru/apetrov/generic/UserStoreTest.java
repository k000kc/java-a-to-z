package ru.apetrov.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 08.03.2017.
 */
public class UserStoreTest {

    private SimpleArray<Base> array;
    private Store store;
    private Base user1;
    private Base user2;

    @Before
    public void init() {
        this.array = new SimpleArray<>(10);
        this.store = new UserStore(this.array);
        this.user1 = new User();
        this.user2 = new User();
        this.store.add(this.user1);
        this.store.add(this.user2);
    }

    @Test
    public void whenAddNewValueThenCheckValue() {
        assertThat(this.array.get(0), is(this.user1));
    }

    @Test
    public void whenUpdateValueThenCheckNewValue() {
        this.store.update("0", this.user1);
        assertThat(this.array.get(0), is(this.user1));
    }

    @Test
    public void whenDeleteValueThenCheckThinPositionToNull() {
        this.store.delete("0");
        assertNull(this.array.get(0));
    }

}