package ru.apetrov.MyTreeSet2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrey on 03.05.2017.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    Node<E> root;

    private int size;

    private Node<E> search(Node<E> node, E parent) {
        Node<E> result = null;
        if (node.value.equals(parent)) {
            result = node;
        } else {
            for (Node<E> child : node.childen) {
                result = this.search(child, parent);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

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

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    private class Node<E> {

        E value;
        List<Node<E>> childen;

        public Node(E value) {
            this.value = value;
            this.childen = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", childen=" + childen +
                    '}';
        }
    }

    private class Itr implements Iterator<E> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return size != this.cursor;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
