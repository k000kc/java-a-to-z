package ru.apetrov.Cycle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 17.03.2017.
 */
public class CycleTest {

    /**
     * Check when the cycle exists.
     */
    @Test
    public void whenThereIsCycleThenResultTrue() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle(first), is(true));
    }

    /**
     * Check when the cycle not exists.
     */
    @Test
    public void whenThereIsNoCycleThenResultFalse() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(null);

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle(first), is(false));
    }

    /**
     * Check when the cycle exists.
     */
    @Test
    public void whenThereIsCycleThenResultTrue2() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(third);

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle2(first), is(true));
    }
}