package ru.apetrov.checkEvenNumbers;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 25.02.2017.
 */
public class CheckEvenNumbersTest {

    @Test
    public void whenGetNextCallShouldPointerForward() {
        CheckEvenNumbers it = new CheckEvenNumbers(new int[]{2, 4});

        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(4));
    }

    @Test
    public void whenCheckNextPositionShoultReturnContantValue() {
        CheckEvenNumbers it = new CheckEvenNumbers(new int[]{2});

        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}