package ru.apetrov.Set;

import java.util.Iterator;

/**
 * Created by Andrey on 27.03.2017.
 * @param <E> type.
 */
public class SimpleSetArray<E> implements SimpleSet<E> {

    /**
     * default capacity of array.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * array.
     */
    private Object[] value;

    /**
     * current index.
     */
    private int index = 0;

    /**
     * Getter array.
     * @return array.
     */
    public Object[] getValue() {
        return value;
    }

    /**
     * Constructor of class.
     * @param initialCapacity of array.
     */
    public SimpleSetArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.value = new Object[initialCapacity];
        } else {
            this.value = new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public boolean add(E e) {
        boolean result = false;
        if (this.isNotDuplicate(e)) {
            this.value[this.index++] = e;
            result = true;
        }
        return result;
    }

    /**
     * search duplicate elements.
     * @param e element.
     * @return true - if not duplicate element.
     */
    public boolean isNotDuplicate(E e) {
        boolean result = true;
        for (Object element : this.value) {
            if (e.equals(element)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * class of iterator.
     */
    private class Itr implements Iterator<E> {

        /**
         * current position.
         */
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return index > this.cursor;
        }

        @Override
        public E next() {
            return (E) value[this.cursor++];
        }
    }
}
