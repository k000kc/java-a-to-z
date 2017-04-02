package ru.apetrov.Set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 01.04.2017.
 */
public class FastSetArrayTest {

    /**
     * check method enlargeCapacity().
     */
    @Test
    public void whenArrayLengthFullThenCreateNewArray() {
        FastSetArray<Integer> set = new FastSetArray<>(1);
        set.add(2);
        set.add(3);
        assertThat(set.getValue().length, is(2));
    }

    /**
     * Automatic sorting checking, when adding new items.
     */
    @Test
    public void whenAddElementsThenGetSortedArray() {
        FastSetArray<String> set = new FastSetArray<>(5);
        set.add("4");
        set.add("8");
        set.add("5");
        set.add("2");
        set.add("7");

        String[] result = new String[5];
        int index = 0;
        for (Object i: set.getValue()) {
            result[index++] = (String) i;
        }

        assertThat(result, is(new String[]{"2", "4", "5", "7", "8"}));

    }
}