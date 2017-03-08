package ru.apetrov.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 08.03.2017.
 */
public class RoleStoreTest {

    private SimpleArray<Base> array;
    private Store store;
    private Base role1;
    private Base role2;

    @Before
    public void init() {
        this.array = new SimpleArray<>(10);
        this.store = new RoleStore(this.array);
        this.role1 = new Role();
        this.role2 = new Role();
        this.store.add(this.role1);
        this.store.add(this.role2);
    }

    @Test
    public void whenAddNewValueThenCheckValue() {
        assertThat(this.array.get(0), is(this.role1));
    }

    @Test
    public void whenUpdateValueThenCheckNewValue() {
        this.store.update("0", this.role2);

        assertThat(this.array.get(0), is(this.role2));
    }

    @Test
    public void whenDeleteValueThenCheckThinPositionToNull() {
        this.store.delete("0");
        assertNull(this.array.get(0));
    }
}