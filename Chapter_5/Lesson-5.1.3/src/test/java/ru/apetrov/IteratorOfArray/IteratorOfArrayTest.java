package ru.apetrov.IteratorOfArray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 12.03.2017.
 */
public class IteratorOfArrayTest {

    /**
     * convert iterator of array in iterator integers.
     */
    @Test
    public void whenThen() {
        IteratorOfNumbers it1 = new IteratorOfNumbers(new int[]{1});
        IteratorOfNumbers it2 = new IteratorOfNumbers(new int[]{1, 2});
        IteratorOfNumbers it3 = new IteratorOfNumbers(new int[]{1, 2, 3});

        IteratorOfArray itA = new IteratorOfArray(new IteratorOfNumbers[]{it1, it2, it3});

        itA.next();
        itA.next();
        itA.next();
        itA.next();
        itA.next();
        Integer result = itA.next();

        assertThat(result, is(3));
    }

}