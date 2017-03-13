package ru.apetrov.list;

import java.util.Iterator;

/**
 * Created by Andrey on 12.03.2017.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

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

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        int cursir = 0;
        Node<E> next;

        @Override
        public boolean hasNext() {
            return cursir != size;
        }

        @Override
        public E next() {
            this.next = first;
            Node<E> result = this.next;
            this.next = this.next.next;
            cursir++;
            return result.item;
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
