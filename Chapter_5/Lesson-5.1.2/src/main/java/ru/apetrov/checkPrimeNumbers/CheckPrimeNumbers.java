package ru.apetrov.checkPrimeNumbers;

import java.util.Iterator;

/**
 * Created by Andrey on 25.02.2017.
 */
public class CheckPrimeNumbers implements Iterator {

    private final int[] values;

    private int index = 0;

    public CheckPrimeNumbers(int[] values) {
        this.values = values;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        if (values.length > this.index && this.isPrime(this.values[this.index])) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result = null;
        if (this.isPrime(this.values[this.index])) {
            result = this.values[this.index];
        }
        this.index++;
        return result;
    }

    private boolean isPrime(int number) {
        boolean result = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
