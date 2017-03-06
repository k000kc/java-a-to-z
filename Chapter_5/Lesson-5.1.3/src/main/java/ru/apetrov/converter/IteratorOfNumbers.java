package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbers implements Iterator {

    private final int[] values;
    private int index = 0;

    public IteratorOfNumbers(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return this.values.length > this.index;
    }

    @Override
    public Object next() {
        return this.values[this.index++];
    }
}
