package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 07.03.2017.
 */
public class IteratorOfIterators implements Iterator {

    /**
     * Iterator of Iterators.
     */
    private Iterator<Iterator<Integer>> bigIterator;

    /**
     * Iterator of Integers.
     */
    private Iterator<Integer> smallIterator;

    /**
     * Constructor of class.
     * @param bigIterator iterator of iterators.
     */
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

    /**
     * method converted iterator of iterators in iterator.
     * @param it iterator of iterators.
     * @return iterator of integer.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return this;
    }
}
