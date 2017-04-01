package ru.apetrov.IteratorOfArray;

import java.util.Iterator;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbers implements Iterator<Integer> {

    /**
     * array of integers.
     */
    private final int[] values;

    /**
     * index of array.
     */
    private int index = 0;

    /**
     * Constructor of class.
     * @param values array integers.
     */
    public IteratorOfNumbers(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return this.values.length > this.index;
    }

    @Override
    public Integer next() {
        return this.values[this.index++];
    }
}
