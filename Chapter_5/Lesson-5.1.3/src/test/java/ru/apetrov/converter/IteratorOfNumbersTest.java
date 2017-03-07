package ru.apetrov.converter;

import org.junit.Test;
import ru.apetrov.converter.IteratorOfArray.IteratorOfArray;
import ru.apetrov.converter.IteratorOfArray.IteratorOfNumbers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbersTest {

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