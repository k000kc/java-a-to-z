package ru.apetrov.SynchronizedList;

import org.junit.Test;
import ru.apetrov.list.SimpleContainer;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 11.07.2017.
 */
public class SynchronizedSimpleArrayTest {

    @Test
    public void whenAddElementThenCheckElement() {
        SimpleContainer<String> array = new SynchronizedSimpleArray<String>();
        array.add("Test0");
        array.add("Test1");
        array.add("Test2");
        array.add("Test3");
        array.add("Test4");
        array.add("Test5");
        array.add("Test6");
        array.add("Test7");
        array.add("Test8");
        array.add("Test9");
        array.add("Test10");
        array.add("Test11");
        assertThat(array.get(11), is("Test11"));
    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenThereAreNoNextElementThenHasNextGetFalse() {
        SimpleContainer<Integer> array = new SynchronizedSimpleArray<Integer>();
        Iterator iterator = array.iterator();
        array.add(1);
        array.add(2);
        iterator.next();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }

    /**
     * check method hasNext().
     */
    @Test
    public void whenThereAreNextElementThenHasNextGetTrue() {
        SimpleContainer<Integer> array = new SynchronizedSimpleArray<Integer>();
        Iterator iterator = array.iterator();
        array.add(1);
        iterator.next();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(true));
    }
}