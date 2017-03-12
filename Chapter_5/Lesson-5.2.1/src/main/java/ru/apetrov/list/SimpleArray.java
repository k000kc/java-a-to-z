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

    private int size;

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
        if (this.elementData.length > this.size) {
            this.elementData[size++] = e;
        } else {
            this.elementData = Arrays.copyOf(this.elementData, this.elementData.length*2);
            this.elementData[size++] = e;
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
            return cursor != size;
        }

        @Override
        public E next() {
            return (E) elementData[this.cursor++];
        }
    }
}
