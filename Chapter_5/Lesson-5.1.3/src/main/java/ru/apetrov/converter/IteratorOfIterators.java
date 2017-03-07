package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 07.03.2017.
 */
public class IteratorOfIterators implements Iterator {

    private Iterator<Iterator<Integer>> bigIterator;

    private Iterator<Integer> smallIterator;

    public IteratorOfIterators(Iterator<Iterator<Integer>> bigIterator) {
        this.bigIterator = bigIterator;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.bigIterator.hasNext() && this.smallIterator.hasNext()) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result;
        if (this.smallIterator == null) {
            this.smallIterator = this.bigIterator.next();
        }
        result = this.smallIterator.next();
        return result;
    }

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return this;
    }
}
