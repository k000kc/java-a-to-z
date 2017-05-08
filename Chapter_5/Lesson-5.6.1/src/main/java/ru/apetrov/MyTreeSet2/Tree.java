package ru.apetrov.MyTreeSet2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrey on 03.05.2017.
 * @param <E> typr.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * root.
     */
    private Node<E> root;

    /**
     * childen list.
     */
    private List<E> childen = new ArrayList<>();

    /**
     * size of tree.
     */
    private int size;

    /**
     * search for a node to insert.
     * @param node node.
     * @param parent parent.
     * @return node.
     */
    private Node<E> search(Node<E> node, E parent) {
        Node<E> result = null;
        if (node.value.equals(parent)) {
            result = node;
        } else {
            for (Node<E> child : node.childen) {
                result = this.search(child, parent);
            }
        }
        return result;
    }

    /**
     * add child to parent.
     * @param parent parent.
     * @param child child.
     * @return true if you can add an child to parent.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> currRoot = this.root;
        if (currRoot != null) {
            Node<E> parentRoot = this.search(currRoot, parent);
            if (parentRoot != null) {
                Node<E> newNode = new Node<>(child);
                parentRoot.childen.add(newNode);
                this.size++;
                result = true;
            }
        } else {
            this.root = new Node<>(child);
            this.size++;
            result = true;
        }
        return result;
    }

    /**
     * write to childen list.
     * @param parent parent.
     */
    private void writeToChildren(Node<E> parent) {
        this.childen.add(parent.value);
        for (Node<E> node : parent.childen) {
            this.writeToChildren(node);
        }
    }

    /**
     * iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        this.writeToChildren(this.root);
        return new Itr();
    }

    /**
     * toString.
     * @return string.
     */
    @Override
    public String toString() {
        return "Tree{" + "root=" + root + '}';
    }

    /**
     * Node.
     * @param <E> type.
     */
    private class Node<E> {

        /**
         * value.
         */
        private E value;

        /**
         * childen list.
         */
        private List<Node<E>> childen;

        /**
         * Constructor.
         * @param value value.
         */
        private Node(E value) {
            this.value = value;
            this.childen = new ArrayList<>();
        }

        /**
         * toString.
         * @return string.
         */
        @Override
        public String toString() {
            return "Node{" + "value=" + value + ", childen=" + childen + '}';
        }
    }

    /**
     * Iterator.
     * @param <E> tipe.
     */
    private class Itr<E> implements Iterator<E> {

        /**
         * current cursor.
         */
        private int cursor = 0;

        /**
         * array of cilden.
         */
        private E[] array = (E[]) childen.toArray();

        @Override
        public boolean hasNext() {
            return size != this.cursor;
        }

        @Override
        public E next() {
            return array[cursor++];
        }
    }
}
