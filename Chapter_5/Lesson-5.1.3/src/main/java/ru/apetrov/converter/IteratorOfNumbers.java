package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 01.03.2017.
 */
public class IteratorOfNumbers implements Iterator<Integer> {

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
        return this.values.length > this.indexX ||
                this.values.length > this.indexY;
    }

    @Override
    public Integer next() {
        Integer result = 0;
        if (this.indexX < this.values.length) {
            if (this.indexY < this.values.length) {
                result = values[this.indexX][this.indexY++];
            } else {
                this.indexX++;
                this.indexY = 0;
                result = values[this.indexX][this.indexY++];
            }
        }
        return result;
    }
}
