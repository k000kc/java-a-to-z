package ru.apetrov.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Andrey on 11.03.2017.
 */
public class SimpleArray<E> implements SimpleContainer<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private Object[] elementData;

    private int index = 0;

    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public SimpleArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E e) {
        if (this.elementData.length > this.index) {
            this.elementData[index++] = e;
        } else {
            this.elementData = Arrays.copyOf(this.elementData, this.elementData.length*2);
            this.elementData[index++] = e;
        }
    }

    @Override
    public E get(int index) {
        return (E) this.elementData[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr<E> implements Iterator<E> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != index;
        }

        @Override
        public E next() {
            return (E) elementData[this.cursor++];
        }
    }
}
