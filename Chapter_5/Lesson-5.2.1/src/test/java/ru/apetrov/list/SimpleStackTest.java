package ru.apetrov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 16.03.2017.
 */
public class SimpleStackTest {

    @Test
    public void whenPushNewElementThenCheckElement() {
        SimpleStack<String> list = new SimpleStack<>();
        String result = list.push("test");
        assertThat(result, is("test"));
    }

    @Test
    public void whenPopElementThenCheckThisElement() {
        SimpleStack<String> list = new SimpleStack<>();
        list.push("test1");
        list.push("test2");
        list.push("test3");

        assertThat(list.pop(), is("test3"));
    }

}