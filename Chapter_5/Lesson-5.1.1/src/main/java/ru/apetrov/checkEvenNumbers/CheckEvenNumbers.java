package ru.apetrov.checkEvenNumbers;

import java.util.Iterator;

/**
 * Created by Andrey on 25.02.2017.
 */
public class CheckEvenNumbers implements Iterator {

    /**
     * Array of numbers.
     */
    private final int[] values;

    /**
     * index of number.
     */
    private int index = 0;

    /**
     * Constructor of class.
     * @param values array of numbers.
     */
    public CheckEvenNumbers(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.values.length > this.index && this.isEven(this.index)) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result = null;
        if (this.isEven(this.index)) {
            result = this.values[this.index];
        }
        this.index++;
        return result;
    }

    /**
     * Check the number is even.
     * @param index index of number.
     * @return true - if number is even.
     */
    private boolean isEven(int index) {
        boolean result = false;
        if (this.values[index] % 2 == 0) {
            result = true;
        }
        return result;
    }
}
