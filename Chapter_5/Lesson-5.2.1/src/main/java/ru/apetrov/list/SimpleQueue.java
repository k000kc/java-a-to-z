package ru.apetrov.list;

/**
 * Created by Andrey on 14.03.2017.
 * @param <E> type.
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {

    @Override
    public void add(E e) {
        super.add(e);
    }

    /**
     * get first element of queue.
     * @return element.
     */
    E peek() {
        return super.get(0);
    }

    /**
     * get first element of queue and remove this element.
     * @return element.
     */
    public E poll() {
        E result = this.peek();
        super.remove(0);
        return result;
    }
}
