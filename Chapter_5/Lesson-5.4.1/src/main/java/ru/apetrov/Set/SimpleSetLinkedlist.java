package ru.apetrov.Set;

import java.util.Iterator;

/**
 * Created by Andrey on 28.03.2017.
 * @param <E> type.
 */
public class SimpleSetLinkedlist<E> implements SimpleSet<E> {

    /**
     * size of set.
     */
    private int size;

    /**
     * first element of set.
     */
    private Node<E> first;

    /**
     * last element of set.
     */
    private Node<E> last;

    @Override
    public boolean add(E e) {
        boolean result = false;
        Node<E> node = new Node<>(e);
        if (this.isNotDuplicate(e)) {
            if (this.size == 0) {
                this.first = node;
            } else {
                this.last.next = node;
                node.prev = this.last;
            }
            this.last = node;
            result = true;
            this.size++;
        }
        return result;
    }

    /**
     * search duplicate elements.
     * @param e element.
     * @return true - if not duplicate element.
     */
    private boolean isNotDuplicate(E e) {
        boolean result = true;
        Node<E> node = first;
        while (node != null) {
            if (node.item.equals(e)) {
                result = false;
            }
            node = node.next;
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

        /**
         * next element.
         */
        private Node<E> node;

        @Override
        public boolean hasNext() {
            return size != this.cursor;
        }

        @Override
        public E next() {
            this.node = first;
            Node<E> result = this.node;
            this.node = this.node.next;
            cursor++;
            return (E) result;
        }
    }

    /**
     * class of node.
     * @param <E> type.
     */
    private class Node<E> {

        /**
         * element.
         */
        private E item;

        /**
         * next element.
         */
        private Node<E> next;

        /**
         * previous element.
         */
        private Node<E> prev;

        /**
         * constructor of class.
         * @param item element.
         */
        private Node(E item) {
            this.item = item;
        }
    }
}
