package ru.apetrov.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 11.03.2017.
 */
public class SimpleArrayTest {

    @Test
    public void whenAddElementThenCheckElement() {
        SimpleArray<String> array = new SimpleArray<>();
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

    @Test
    public void whenThereAreNoNextElementThenHasNextGetFalse() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        Iterator iterator = array.iterator();
        array.add(1);
        array.add(2);
        iterator.next();
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenThereAreNextElementThenHasNextGetTrue() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        Iterator iterator = array.iterator();
        array.add(1);
        array.add(2);
        iterator.next();
        boolean result = iterator.hasNext();
        assertThat(result, is(true));
    }
}