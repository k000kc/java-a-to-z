package ru.apetrov.Cycle;

/**
 * Created by Andrey on 17.03.2017.
 */
public class Cycle {

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
}
