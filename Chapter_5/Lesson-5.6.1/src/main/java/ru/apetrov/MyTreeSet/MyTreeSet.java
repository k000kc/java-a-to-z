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

        Leaf<E> currRoot = null;
        Leaf<E> oldRoot = this.root;

        while (oldRoot != null) {
            currRoot = oldRoot;
            if (e.hashCode() < oldRoot.item.hashCode()) {
                oldRoot = oldRoot.left;
            } else {
                oldRoot = oldRoot.right;
            }
        }

        if (currRoot == null) {
            this.root = new Leaf<>(e, null);
            this.size++;
            result = true;
        } else if (e.hashCode() < currRoot.item.hashCode()) {
            currRoot.left = new Leaf<>(e, currRoot.item);
            this.size++;
            result = true;
        } else if (e.hashCode() > currRoot.item.hashCode()){
            currRoot.right = new Leaf<>(e, currRoot.item);
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
