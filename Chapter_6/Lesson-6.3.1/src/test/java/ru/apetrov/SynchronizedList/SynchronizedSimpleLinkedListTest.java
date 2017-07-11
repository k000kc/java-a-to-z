package ru.apetrov.SynchronizedList;

import org.junit.Test;
import ru.apetrov.list.SimpleContainer;

import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 11.07.2017.
 */
public class SynchronizedSimpleLinkedListTest {

    /**
     * check method add().
     */
    @Test
    public void whenAddNewElementThenChekThisElement() {
        SimpleContainer<String> list = new SynchronizedSimpleLinkedList<String>();
        list.add("test1");
        list.add("test2");

        assertThat(list.get(1), is("test2"));
    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenNotHasNextThenGetFalse() {
        SimpleContainer<String> list = new SynchronizedSimpleLinkedList<String>();
        list.add("test");
        list.add("test");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());

    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenHasNextThenGetTrue() {
        SimpleContainer<String> list = new SynchronizedSimpleLinkedList<String>();
        list.add("test");
        list.add("test");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }
}