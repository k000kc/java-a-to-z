package ru.apetrov.Set;

import java.util.Iterator;

/**
 * Created by Andrey on 28.03.2017.
 */
public class SimpleSetLinkedlist<E> implements SimpleSet<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public int getSize() {
        return size;
    }

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

    private class Itr implements Iterator<E> {

        int cursor = 0;
        Node<E> node;

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

    private class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this.item = item;
        }
    }
}
