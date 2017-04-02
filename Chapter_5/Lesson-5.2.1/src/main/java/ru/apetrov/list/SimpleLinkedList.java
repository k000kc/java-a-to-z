package ru.apetrov.list;

import java.util.Iterator;

/**
 * Created by Andrey on 12.03.2017.
 * @param <E> type.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {

    /**
     * size of list.
     */
    private int size;

    /**
     * first element of list.
     */
    private Node<E> first;

    /**
     * last element of list.
     */
    private Node<E> last;

    /**
     * getter of size.
     * @return size.
     */
    public int getSize() {
        return size;
    }

    @Override
    public void add(E e) {
        Node<E> node = new Node<>(e);
        if (this.size == 0) {
            this.first = node;
        } else {
            this.last.next = node;
            node.prev = this.last;
        }
        this.last = node;
        this.size++;
    }

    @Override
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    /**
     * remove element by index.
     * @param index index of ekement.
     */
    public void remove(int index) {
        //удаление в начале
        if (index == 0) {
            this.first = this.first.next;
            this.first.prev = null;
        //удаление в конце
        } else if (index == this.size - 1) {
            this.last.prev.next = null;
            this.last = this.last.prev;
        //удаление в середине
        } else {
            Node<E> node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            node.item = null;
        }
        this.size--;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Class of iterator.
     */
    private class Itr implements Iterator<E> {

        /**
         * current position.
         */
        private int cursor = 0;

        /**
         * next element.
         */
        private Node<E> next;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            this.next = first;
            Node<E> result = this.next;
            this.next = this.next.next;
            cursor++;
            return result.item;
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
         * Constructor of class.
         * @param item element.
         */
        private Node(E item) {
            this.item = item;
        }
    }
}
