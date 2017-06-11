package ru.apetrov.Task2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Andrey on 11.06.2017.
 */
public class SearchNumbersTest {

    /**
     * check method search().
     */
    @Test
    public void whenPassArrayTenGetResultArray() {
        SearchNumbers searchNumbers = new SearchNumbers(new int[]{1, 2, 3, 5, 6, 7, 8, 10, 11, 12});
        int[] result = searchNumbers.search();

        assertThat(result, is(new int[]{4, 9}));
    }
}