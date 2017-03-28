package ru.apetrov.list;

import java.util.Iterator;

/**
 * Created by Andrey on 12.03.2017.
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

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

    private class Itr implements Iterator<E> {

        int cursor = 0;
        Node<E> next;

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


    private class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this.item = item;
        }
    }
}
