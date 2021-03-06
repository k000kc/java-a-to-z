package ru.apetrov.IteratorOfArray;

import java.util.Iterator;

/**
 * Created by Andrey on 06.03.2017.
 */
public class IteratorOfArray implements Iterator {

    /**
     * iterator of numbers.
     */
    private final IteratorOfNumbers[] values;

    /**
     * index of values.
     */
    private int index = 0;

    /**
     * Constructor of class.
     * @param values array of arrays.
     */
    public IteratorOfArray(IteratorOfNumbers[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.values[this.index].hasNext() || this.values.length > this.index) {
            result = true;
        }
        return result;
    }

    @Override
    public Integer next() {
        int result = 0;
        if (this.values[index].hasNext()) {
            result = this.values[this.index].next();
        } else {
            this.index++;
            result = this.values[this.index].next();
        }
        return result;
    }
}
