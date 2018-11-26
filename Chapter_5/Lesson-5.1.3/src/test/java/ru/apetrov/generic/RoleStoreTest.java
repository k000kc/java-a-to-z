package ru.apetrov.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 08.03.2017.
 */
public class RoleStoreTest {

    /**
     * simple array.
     */
    private SimpleArray array;

    /**
     * role dao.
     */
    private RoleStore store;

    /**
     * role1.
     */
    private Role role1;

    /**
     * role2.
     */
    private Role role2;

    /**
     * init.
     */
    @Before
    public void init() {
        this.store = new RoleStore(10);
        this.role1 = new Role("1");
        this.role2 = new Role("2");
        this.store.add(this.role1);
        this.store.add(this.role2);
        this.array = this.store.getArray();
    }

    /**
     * check mathod get().
     */
    @Test
    public void whenAddNewValueThenCheckValue() {
        assertThat(this.array.get(0), is(this.role1));
    }

    /**
     * Check method update.
     */
    @Test
    public void whenUpdateValueThenCheckNewValue() {
        this.store.update("1", this.role2);
        assertThat(this.array.get(0), is(this.role2));
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
        RoleStore roleStore = new RoleStore(10);
        Role roleTest = new Role("test1");
        roleStore.add(roleTest);
        simpleArray = roleStore.getArray();
        assertThat(simpleArray.get(0), is(roleTest));
    }
}