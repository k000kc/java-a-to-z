package ru.apetrov.MyTreeSet;

import java.util.List;

/**
 * Created by Andrey on 30.04.2017.
 */
public class MyTreeSet<E> implements SimpleTree<E> {

    private Leaf<E> root;

    private int size = 0;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean addChild(E e) {
        boolean result = false;

        Leaf<E> oldRoot = null;
        Leaf<E> currRoot = this.root;

        while (currRoot != null) {
            oldRoot = currRoot;
            if (e.hashCode() < currRoot.item.hashCode()) {
                currRoot = currRoot.left;
            } else {
                currRoot = currRoot.right;
            }
        }

        if (oldRoot == null) {
            this.root = new Leaf<>(e, null);
            this.size++;
            result = true;
        } else if (e.hashCode() < oldRoot.item.hashCode()) {
            oldRoot.left = new Leaf<>(e, oldRoot.item);
            this.size++;
            result = true;
        } else {
            oldRoot.right = new Leaf<>(e, oldRoot.item);
            this.size++;
            result = true;
        }

        return result;
    }

    @Override
    public List<E> getChildren(E e) {
        return null;
    }

    @Override
    public String toString() {
        return "MyTreeSet{" +
                "root=" + root +
                '}';
    }

    private class Leaf<E> {

        private E item;
        private Leaf<E> left;
        private Leaf<E> right;
        private E parent;

        public Leaf(E item, E parent) {
            this.item = item;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Leaf{" +
                    "item=" + item +
                    ", left=" + left +
                    ", right=" + right +
                    ", parent=" + parent +
                    '}';
        }
    }
}
