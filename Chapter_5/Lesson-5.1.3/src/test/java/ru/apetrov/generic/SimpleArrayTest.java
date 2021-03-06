package ru.apetrov.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 07.03.2017.
 */
public class SimpleArrayTest {

    /**
     * check method add() by string.
     */
    @Test
    public void whenCreateContainterShouldReturnTheSameType() {
        SimpleArray<String> simple = new SimpleArray<>(4);
        simple.add("test");
        String result = simple.get(0);

        assertThat(result, is("test"));
    }

    /**
     * check method add() by  integer.
     */
    @Test
    public void whenTypeIntShouldReturnInt() {
        SimpleArray<Integer> simple = new SimpleArray<>(4);
        simple.add(2);
        int result = simple.get(0);

        assertThat(result, is(2));
    }

    /**
     * check method delete().
     */
    @Test
    public void whenDeleteValueThenCheckNullPosition() {
        SimpleArray<String> simple = new SimpleArray<>(1);
        simple.add("test");
        simple.delete(0);

        assertNull(simple.get(0));
    }

    /**
     * check method update().
     */
    @Test
    public void whenUpdateValueThenNewValue() {
        SimpleArray<String> simple = new SimpleArray<>(1);
        simple.add("test");
        simple.update(0, "NewTest");

        assertThat(simple.get(0), is("NewTest"));
    }
}