package ru.apetrov.Cycle;

/**
 * Created by Andrey on 17.03.2017.
 * @param <E> type.
 */
public class Cycle<E> {

    /**
     * check for cycle.
     * @param first element by list.
     * @return true - if cycle exists.
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        while (first != null) {
            if (first.isVisited()) {
                result = true;
                break;
            }
            first.setVisited(true);
            first = first.getNext();
        }
        return result;
    }

    /**
     * check for cycle.
     * @param first element by list.
     * @return true - if cycle exists.
     */
    boolean hasCycle2(Node first) {
        boolean result = false;
        Node temp = first;
        while (first != null && temp != null && temp.getNext() != null) {
            first = first.getNext();
            temp = temp.getNext().getNext();
            if (first == temp) {
                result = true;
                break;
            }
        }
        return result;
    }
}
