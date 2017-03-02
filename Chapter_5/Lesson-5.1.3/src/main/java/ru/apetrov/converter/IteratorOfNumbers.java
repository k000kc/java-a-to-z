package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbers implements Iterator {

    private final int[][] values;
    private int[] res;

    private int indexX = 0;
    private int indexY = 0;


    public IteratorOfNumbers(int[][] values) {
        this.values = values;
        this.res = new int[values.length * values.length];
    }

    @Override
    public boolean hasNext() {
        return this.values.length > this.indexX &&
                this.values.length > this.indexY;
    }

    @Override
    public int[] next() {
        int destPos = 0;
        for (int[] x: values) {
            System.arraycopy(x, 0, this.res, destPos, x.length);
            destPos += x.length;
        }
        return this.res;
    }
}
