package ru.apetrov.list;

import java.util.EmptyStackException;

/**
 * Created by Andrey on 16.03.2017.
 */
public class SimpleStack<E> extends SimpleLinkedList<E> {

    public E push(E e) {
        super.add(e);
        return e;
    }

    public E pop() {
        E obj;
        int len = super.getSize();
        obj = this.peek();
        super.remove(len - 1);
        return obj;
    }

    public E peek() {
        int len = super.getSize();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return super.get(len - 1);
    }

}
