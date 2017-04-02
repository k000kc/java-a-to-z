package ru.apetrov.Set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Andrey on 01.04.2017.
 * @param <E> type.
 */
public class FastSetArray<E> implements SimpleSet<E> {

    /**
     * default capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * array of elements.
     */
    private Object[] value;

    /**
     * size of array.
     */
    private int size = 0;

    /**
     * Constructor of class.
     * @param initialCapacity init capacity of array.
     */
    public FastSetArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.value = new Object[initialCapacity];
        } else {
            this.value = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * getter of value.
     * @return array.
     */
    public Object[] getValue() {
        return value;
    }

    @Override
    public boolean add(E e) {
        boolean result = false;
        this.enlargeCapacity();
        int position = this.binarySearchPosition(this.value, 0, this.size - 1, e);
        if (position != -1) {
            System.arraycopy(this.value, position, this.value, position + 1, this.size - position);
            this.value[position] = e;
            this.size++;
            result = true;
        }
        return result;
    }

    /**
     * Binary search for a position for inserting an element.
     * @param array array.
     * @param first position.
     * @param last position.
     * @param e element.
     * @return position for inserting.
     */
    private int binarySearchPosition(Object[] array, int first, int last, E e) {
        int mid = first + (last - first) / 2;
        if (first > last) {
            return mid;
        }
        if (e.hashCode() < array[mid].hashCode()) {
            return binarySearchPosition(array, first, mid - 1, e);
        } else if (e.hashCode() > array[mid].hashCode()) {
            return binarySearchPosition(array, mid + 1, last, e);
        } else {
            return -1;
        }
    }

    /**
     * Increase capacity.
     */
    private void enlargeCapacity() {
        if (this.size == this.value.length) {
            this.value = Arrays.copyOf(this.value, this.value.length * 2);
        }
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    /**
     * class iterator.
     */
    private class Itr implements Iterator<E> {

        /**
         * current position.
         */
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return size > this.cursor;
        }

        @Override
        public E next() {
            return (E) value[this.cursor++];
        }
    }
}
