package ru.apetrov.Task;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 07.06.2017.
 */
public class ComboArraysTest {

    /**
     * Check result array.
     */
    @Test
    public void when() {
        int[] a = new int[]{1, 4, 8, 12, 15, 18};
        int[] b = new int[] {2, 1, 7, 2, 4, 0, 15};
        ComboArrays comboArrays = new ComboArrays(a, b);

        int[] result = comboArrays.combo();
        assertThat(result, is(new int[]{0, 1, 1, 2, 2, 4, 4, 7, 8, 12, 15, 15, 18}));
    }



}