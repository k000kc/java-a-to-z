package ru.apetrov.Set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 27.03.2017.
 */
public class SimpleSetArrayTest {

    @Test
    public void whenAddNewElementThenAddGetTrue() {
        SimpleSet<Integer> set = new SimpleSetArray<>(4);
        assertTrue(set.add(2));
    }

    @Test
    public void whenAddDuplicateElementThenAddGetFalse() {
        SimpleSet<Integer> set = new SimpleSetArray<>(4);
        set.add(1);
        assertFalse(set.add(1));
    }

    @Test
    public void whenNextElementThenCheckThisElenent() {
        SimpleSet<Integer> set = new SimpleSetArray<>(4);

        set.add(2);
        set.add(3);
        set.add(4);

        Iterator iterator = set.iterator();
        iterator.next();

        assertThat(iterator.next(), is(3));
    }

    @Test
    public void whenAddElementsThenCheckSortingArray() {
        SimpleSet<Integer> set = new SimpleSetArray<>(5);

        set.add(7);
        set.add(5);
        set.add(4);
        set.add(3);
        set.add(1);

        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}