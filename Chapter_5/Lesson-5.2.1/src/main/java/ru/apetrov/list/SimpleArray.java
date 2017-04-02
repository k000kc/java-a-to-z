package ru.apetrov.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Andrey on 11.03.2017.
 * @param <E> type.
 */
public class SimpleArray<E> implements SimpleContainer<E> {

    /**
     * default capacity of elementData.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * elementData.
     */
    private Object[] elementData;

    /**
     * size of elementData.
     */
    private int size;

    /**
     * Constructor of class.
     * @param initialCapacity capacity of elementData.
     */
    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            this.elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * default constructor of class.
     */
    public SimpleArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E e) {
        if (this.elementData.length == this.size) {
            this.elementData = Arrays.copyOf(this.elementData, this.elementData.length * 2);
        }
        this.elementData[size++] = e;
    }

    @Override
    public E get(int index) {
        return (E) this.elementData[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Class iterator.
     * @param <E> type.
     */
    private class Itr<E> implements Iterator<E> {

        /**
         * current position.
         */
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            return (E) elementData[this.cursor++];
        }
    }
}
