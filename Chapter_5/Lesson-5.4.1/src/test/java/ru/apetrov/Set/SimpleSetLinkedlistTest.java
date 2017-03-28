package ru.apetrov.Set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 28.03.2017.
 */
public class SimpleSetLinkedlistTest {

    @Test
    public void whenAddNewElementThenCheckGetTrue() {
        SimpleSetLinkedlist<String> set = new SimpleSetLinkedlist<>();
        boolean res = set.add("test");
        assertThat(res, is(true));
    }

    @Test
    public void whenAddDuplicateElementThenCheckGetFalse() {
        SimpleSetLinkedlist<String> set = new SimpleSetLinkedlist<>();
        set.add("test");
        boolean res = set.add("test");
        assertThat(res, is(false));
    }

    @Test
    public void whenHasNextElementThenCheckGetTrue() {
        SimpleSetLinkedlist<String> set = new SimpleSetLinkedlist<>();

        set.add("test1");
        set.add("test2");
        set.add("test3");

        Iterator<String> iterator = set.iterator();
        iterator.next();

        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenHasNextNotElementThenCheckGrtFalse() {
        SimpleSetLinkedlist<String> set = new SimpleSetLinkedlist<>();

        set.add("test1");
        set.add("test2");

        Iterator<String> iterator = set.iterator();
        iterator.next();
        iterator.next();

        assertThat(iterator.hasNext(), is(false));
    }
}