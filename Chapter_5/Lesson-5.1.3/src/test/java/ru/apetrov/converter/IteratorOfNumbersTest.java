package ru.apetrov.converter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbersTest {

    @Test
    public void whenThen() {
        IteratorOfNumbers it = new IteratorOfNumbers(new int[]{1, 2, 3, 4, 5});

        it.next();
        int result = it.next();

        assertThat(result, is(2));
    }

    @Test
    public void whenNotHasNextThenFalse() {
        IteratorOfNumbers it = new IteratorOfNumbers(new int[]{1});

        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}