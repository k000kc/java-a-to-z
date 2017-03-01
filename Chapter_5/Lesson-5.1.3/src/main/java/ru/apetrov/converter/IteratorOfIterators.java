package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 28.02.2017.
 */
public class IteratorOfIterators implements Iterator<Iterator<Integer>> {

    private Iterator<Integer>[] iterator;

    private int index = 0;

    public IteratorOfIterators(Iterator<Integer>[] iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.length > this.index;
    }

    @Override
    public Iterator<Integer> next() {
        return this.iterator[index++];
    }

}
