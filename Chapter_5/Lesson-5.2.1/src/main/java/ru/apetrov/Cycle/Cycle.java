package ru.apetrov.Cycle;

/**
 * Created by Andrey on 17.03.2017.
 */
public class Cycle<E> {

    boolean hasCycle(Node first) {
        boolean result = false;
        while (first != null) {
            if (first.isVisited == true) {
                result = true;
                break;
            }
            first.isVisited = true;
            first = first.next;
        }
        return result;
    }

    boolean hasCycle2(Node first) {
        boolean result = false;
        Node<E> temp = first;
        while (first != null && temp != null && temp.next != null) {
            first = first.next;
            temp = temp.next.next;
            if (first == temp) {
                result = true;
                break;
            }
        }
        return result;
    }
}
