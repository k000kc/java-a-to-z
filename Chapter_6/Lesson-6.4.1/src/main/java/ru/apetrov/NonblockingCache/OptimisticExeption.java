package ru.apetrov.NonblockingCache;

/**
 * Created by Andrey on 17.08.2017.
 */
public class OptimisticExeption extends RuntimeException {

    /**
     * Constructor.
     * @param msg msg.
     */
    public OptimisticExeption(String msg) {
        System.err.println(msg);
    }
}
