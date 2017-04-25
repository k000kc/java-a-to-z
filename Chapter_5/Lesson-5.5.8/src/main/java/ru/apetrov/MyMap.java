package ru.apetrov;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Andrey on 23.04.2017.
 * @param <T> key.
 * @param <V> value.
 */
public class MyMap<T, V> implements SimpleContainer<T, V> {

    /**
     * array of nodes.
     */
    private Node<T, V>[] nodes;

    /**
     * default capacity of array.
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * size of array.
     */
    private int size = 0;

    /**
     * Constructor.
     * @param initialCapacity capacity of array.
     */
    public MyMap(int initialCapacity) {
        if (initialCapacity > 0) {
            this.nodes = new Node[initialCapacity];
        } else {
            this.nodes = new Node[DEFAULT_CAPACITY];
        }
    }

    /**
     * position in array.
     * @param key key.
     * @return position in array.
     */
    private int positionInNodes(T key) {
        int result = 0;
        if (key != null) {
            result = key.hashCode() % this.nodes.length;
        }
        return result;
    }

    /**
     * add element in array.
     * @param key key.
     * @param value value.
     * @return true - If you can insert an element.
     */
    @Override
    public boolean insert(T key, V value) {
        boolean result = false;
        int index = this.positionInNodes(key);
        if (this.nodes[index] == null) {
            this.nodes[index] = new Node<>(key, value);
            this.size++;
            result = true;
        } else {
            Node<T, V> temp = nodes[index];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    temp.value = value;
                    result = true;
                    break;
                } else if (temp.next == null) {
                    temp.next = new Node<>(key, value);
                    this.size++;
                    result = true;
                    break;
                }
                temp = temp.next;
            }
        }
        return result;
    }

    /**
     * get value.
     * @param key key.
     * @return value.
     */
    @Override
    public V get(T key) {
        V result = null;
        int index = this.positionInNodes(key);
        Node<T, V> curr = this.nodes[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                result = curr.value;
                break;
            }
            curr = curr.next;
        }
        return result;
    }

    /**
     * delete.
     * @param key key.
     * @return true - If you can remove an element.
     */
    @Override
    public boolean delete(T key) {
        boolean result = false;
        int index = this.positionInNodes(key);
        Node<T, V> curr = this.nodes[index];
        Node<T, V> prev = curr;
        curr = curr.next;
        while (curr != null) {
            if (prev.key.equals(key)) {
                this.nodes[index] = prev.next;
                this.size--;
                result = true;
            }
            if (curr.key.equals(key)) {
                prev.next = curr.next;
                curr = null;
                this.size--;
                result = true;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        return result;
    }

    @Override
    public String toString() {
        return "MyMap{" + "nodes=" + Arrays.toString(nodes) + '}';
    }

    /**
     * iterator.
     * @return iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new Itr();
    }

    /**
     * Iterator.
     */
    private class Itr implements Iterator<V> {

        /**
         * position in array(nodes).
         */
        private int nodeCursor = 0;

        /**
         * index of iterator.
         */
        private int position = 0;

        /**
         * current node.
         */
        private Node<T, V> curr = nodes[this.nodeCursor];

        @Override
        public boolean hasNext() {
            return this.position < size;
        }

        @Override
        public V next() {
            V result = null;
            if (curr != null) {
                if (curr.next == null) {
                    this.nodeCursor++;
                }
                result = curr.value;
                this.curr = this.curr.next;
            } else {
                this.nodeCursor++;
                this.curr = nodes[this.nodeCursor];
            }
            this.position++;
            return result;
        }
    }

    /**
     * Node.
     * @param <T> key.
     * @param <V> value.
     */
    private class Node<T, V> {

        /**
         * key.
         */
        private T key;

        /**
         * value.
         */
        private V value;

        /**
         * next node.
         */
        private Node<T, V> next;

        /**
         * Constructor.
         * @param key key.
         * @param value value.
         */
        private Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + ", next=" + next + '}';
        }
    }
}
