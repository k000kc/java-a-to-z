package ru.apetrov.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 13.03.2017.
 */
public class SimpleLinkedListTest {

    @Test
    public void whenAddNewElementThenChekThisElement() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("test");
        list.add("test");

        assertThat(list.get(1), is("test"));
    }

    @Test
    public void whenNotHasNextThenGetFalse() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("test");
        list.add("test");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

}