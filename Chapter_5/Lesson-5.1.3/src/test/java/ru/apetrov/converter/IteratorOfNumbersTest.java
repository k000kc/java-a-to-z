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
        IteratorOfNumbers it = new IteratorOfNumbers(new int[][]{{1, 2, 3},
                                                                 {4, 5, 6},
                                                                 {7, 8, 9}});

        int[] result = it.next();
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertThat(result, is(expected));
    }
}