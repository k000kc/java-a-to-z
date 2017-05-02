package ru.apetrov.MyTreeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 30.04.2017.
 * @param <E> element type.
 */
public class MyTreeSet<E> implements SimpleTree<E> {

    /**
     * root.
     */
    private Leaf<E> root;

    /**
     * size.
     */
    private int size = 0;

    /**
     * get size.
     * @return size.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * add element.
     * @param e element.
     * @return true - If you can add an element.
     */
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
        } else if (e.hashCode() > currRoot.item.hashCode()) {
            currRoot.right = new Leaf<>(e, currRoot.item);
            this.size++;
            result = true;
        }

        return result;
    }

    /**
     * get list elements.
     * @return list.
     */
    @Override
    public List<E> getChildren() {
        List<E> result = new ArrayList<E>();
        Leaf<E> curRoot = this.root;
        while (curRoot != null && !result.contains(curRoot.item)) {
            if (curRoot.left != null && !result.contains(curRoot.left.item)) {
                curRoot = curRoot.left;
            } else {
                result.add(curRoot.item);
                if (curRoot.right != null && !result.contains(curRoot.right.item)) {
                    curRoot = curRoot.right;
                } else {
                    curRoot = this.root;
                }
            }
        }
        return result;
    }

    /**
     * search to element.
     * @param e element.
     * @return
     */
    public E search(E e) {
        E result = null;
        Leaf<E> curRoot = this.root;
        while (curRoot != null && e.hashCode() != curRoot.item.hashCode()) {
            if (e.hashCode() < curRoot.item.hashCode()) {
                curRoot = curRoot.left;
            } else {
                curRoot = curRoot.right;
            }
        }
        if (curRoot != null) {
            result = curRoot.item;
        }
        return result;
    }

    @Override
    public String toString() {
        return "MyTreeSet{" + "root=" + root + '}';
    }

    /**
     * Leaf.
     * @param <E> type of leaf.
     */
    private class Leaf<E> {

        /**
         * element.
         */
        private E item;

        /**
         * left leaf.
         */
        private Leaf<E> left;

        /**
         * right leaf.
         */
        private Leaf<E> right;

        /**
         * parent element.
         */
        private  E parent;

        /**
         * Constructor.
         * @param item element.
         * @param parent parent element.
         */
        private Leaf(E item, E parent) {
            this.item = item;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Leaf{" + "item=" + item + ", left=" + left + ", right=" + right + ", parent=" + parent + '}';
        }
    }
}
