package ru.apetrov.Query;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 31.10.2017.
 */
public class QueryFiltersTest {

    /**
     * запрос с фильтром равно.
     */
    @Test
    public void testEquallyFilter() {
        QueryFilters queryFilters = new QueryFilters("*", "user");
        Filter filter = queryFilters.select(0);
        String result = filter.execute("id", "5");
        String expected = "SELECT * FROM user AS u WHERE u.id = 5;";
        assertThat(expected, is(result));
    }

    /**
     * запрос с фильтром больше.
     */
    @Test
    public void testLargerFilter() {
        QueryFilters queryFilters1 = new QueryFilters("*", "user");
        Filter filter = queryFilters1.select(1);
        String result = filter.execute("id", "5");
        String expected = "SELECT * FROM user AS u WHERE u.id > 5;";
        assertThat(expected, is(result));
    }

    /**
     * запрос с фильтром меньше.
     */
    @Test
    public void testSmallerFilter() {
        QueryFilters queryFilters2 = new QueryFilters("*", "user");
        Filter filter = queryFilters2.select(2);
        String result = filter.execute("id", "5");
        String expected = "SELECT * FROM user AS u WHERE u.id < 5;";
        assertThat(expected, is(result));
    }

    /**
     * запрос с фильтром содержит.
     */
    @Test
    public void testContainsFilter() {
        QueryFilters queryFilters3 = new QueryFilters("*", "user");
        Filter filter = queryFilters3.select(3);
        String result = filter.execute("id", "DIT");
        String expected = "SELECT * FROM user AS u WHERE u.id LIKE '%DIT%';";
        assertThat(expected, is(result));
    }

}