package ru.apetrov.checkPrimeNumbers;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 25.02.2017.
 */
public class CheckPrimeNumbersTest {

    @Test
    public void whenGetNextCallShouldPointerForward() {
        CheckPrimeNumbers it = new CheckPrimeNumbers(new int[]{1, 2, 3, 5, 7});

        it.next();
        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(3));
    }

    @Test
    public void whenCheckNextPositionShoultReturnContantValue() {
        CheckPrimeNumbers it = new CheckPrimeNumbers(new int[]{1, 4});

        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }

}