package ru.apetrov.Cycle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 17.03.2017.
 */
public class CycleTest {

    @Test
    public void whenThereIsCycleThenResultTrue() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle(first), is(true));
    }

    @Test
    public void whenThereIsNoCycleThenResultFalse() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle(first), is(false));
    }

    @Test
    public void whenThereIsCycleThenResultTrue2() {
        Node first = new Node();
        Node two = new Node();
        Node third = new Node();
        Node four = new Node();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = third;

        Cycle cycle = new Cycle();
        assertThat(cycle.hasCycle2(first), is(true));
    }
}