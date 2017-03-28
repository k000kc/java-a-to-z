package ru.apetrov.Set;

import java.util.Iterator;

/**
 * Created by Andrey on 27.03.2017.
 */
public class SimpleSetArray<E> implements SimpleSet<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] value;

    private int index = 0;

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

    private class Itr implements Iterator<E> {

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
