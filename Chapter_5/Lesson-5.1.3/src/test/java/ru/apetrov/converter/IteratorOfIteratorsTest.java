package ru.apetrov.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 07.03.2017.
 */
public class IteratorOfIteratorsTest {

    @Test
    public void whenConvertIteratorOfIteratorsThenGetIterator() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayList<Iterator<Integer>> bigList = new ArrayList();
        bigList.add(list.iterator());
        bigList.add(list.iterator());
        IteratorOfIterators it = new IteratorOfIterators(bigList.iterator());
        Iterator<Integer> result = it.convert(it);

        assertThat(result.next(), is(1));
    }
}