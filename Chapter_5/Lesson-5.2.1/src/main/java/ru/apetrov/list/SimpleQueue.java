package ru.apetrov.list;

/**
 * Created by Andrey on 14.03.2017.
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {

    @Override
    public void add(E e) {
        super.add(e);
    }

    E peek() {
        return super.get(0);
    }

    public E poll() {
        E result = this.peek();
        super.remove(0);
        return result;
    }
}
