package ru.apetrov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 06.07.2017.
 */
public class CountTest {

    @Test
    public void whenIncrementCountThenGetResult() {
        Count count = new Count();
        count.increment();
        assertThat(1, is(count.getCount()));
    }
}