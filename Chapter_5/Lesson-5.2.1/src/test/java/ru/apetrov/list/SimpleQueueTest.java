package ru.apetrov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 16.03.2017.
 */
public class SimpleQueueTest {

    @Test
    public void whenAddElementThenGetElement() {
        SimpleQueue<String> list = new SimpleQueue<>();
        list.add("test1");
        assertThat(list.peek(), is("test1"));
    }

    @Test
    public void whenPollElementThenGetElement() {
        SimpleQueue<String> list = new SimpleQueue<>();
        list.add("test1");
        list.add("test2");
        String result = list.poll();
        assertThat(result, is("test1"));
    }
}