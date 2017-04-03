package ru.apetrov.Set;

import org.junit.Test;

import java.util.Iterator;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by Andrey on 27.03.2017.
 */
public class SimpleSetArrayTest {

    /**
     * check method add().
     */
    @Test
    public void whenAddNewElementThenAddGetTrue() {
        SimpleSet<Integer> set = new SimpleSetArray<>(4);
        assertTrue(set.add(2));
    }

    /**
     * check method add().
     */
    @Test
    public void whenAddDuplicateElementThenAddGetFalse() {
        SimpleSet<Integer> set = new SimpleSetArray<>(4);
        set.add(1);
        assertFalse(set.add(1));
    }

    /**
     * check method next().
     */
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

//    @Test
//    public void whenAddElementsThenCheckSortingArray() {
//        SimpleSet<Integer> set = new SimpleSetArray<>(5);
//
//        set.add(7);
//        set.add(5);
//        set.add(4);
//        set.add(3);
//        set.add(1);
//
//        Iterator<Integer> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }

    /**
     * Insert speed test.
     */
    @Test
    public void test() {
        SimpleSetArray<Integer> set = new SimpleSetArray<>(100000);

        long start = currentTimeMillis();

        for (int i = 99999; i >= 0; i--) {
            set.add((int) Math.round(Math.random()*100000));
        }
        long stop = currentTimeMillis();
        System.out.println(stop - start);
        System.out.println();

        for (Object i : set.getValue()) {
            System.out.println(i);
        }
    }
}