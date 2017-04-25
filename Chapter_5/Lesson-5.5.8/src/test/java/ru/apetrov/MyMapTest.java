package ru.apetrov;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

/**
 * Created by Andrey on 23.04.2017.
 */
public class MyMapTest {

    /**
     * check method insert().
     */
    @Test
    public void whenInsertNewItemThenGetResultTrue() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("key", "value");
        map.insert("sey", "value");
        map.insert("wey", "value");
        boolean result =  map.insert("sey", "newValue");

        System.out.println(map);

        assertThat(result, is(true));
    }

    /**
     * check method get().
     */
    @Test
    public void whenInputKeyThenGetValueThisKey() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("1", "value1");
        map.insert("2", "value2");
        map.insert("3", "value3");
        map.insert("2", "newValue2");

        System.out.println(map.get("2"));

        assertThat(map.get("2"), is("newValue2"));
    }

    /**
     * check method delete().
     */
    @Test
    public void whenInputKeyThenRemoveValueThisKek() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("key", "valueKey");
        map.insert("sey", "valueSey");
        map.insert("wey", "valueWey");
        map.delete("sey");

        System.out.println(map);

        assertNull(map.get("sey"));
    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenHasNextThenGetTrue() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("1", "1");
        map.insert("2", "2");
        Iterator<String> iterator = map.iterator();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(true));

    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenNotHasNextThenGetFalse() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("1", "1");
        Iterator<String> iterator = map.iterator();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(false));

    }

    /**
     * check method next().
     */
    @Test
    public void whenNextThenGetCurrentElement() {
        SimpleContainer<String, String> map = new MyMap<>(4);
        map.insert("1", "2");
        map.insert("key", "valueKey");
        map.insert("sey", "valueSey");
        map.insert("wey", "valueWey");

        System.out.println(map);
        Iterator<String> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        String result = iterator.next();
        System.out.println(result);

        assertThat(result, is("valueWey"));
    }

}