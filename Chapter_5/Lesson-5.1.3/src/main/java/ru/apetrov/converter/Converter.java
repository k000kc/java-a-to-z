package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 06.03.2017.
 */
public class Converter {

    private IteratorOfNumbers iteratorOfNumbers;
    private IteratorOfArray iteratorOfArray;

    public Converter(IteratorOfNumbers iteratorOfNumbers, IteratorOfArray iteratorOfArray) {
        this.iteratorOfNumbers = iteratorOfNumbers;
        this.iteratorOfArray = iteratorOfArray;
    }

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        Iterator<Integer> result = null;


        return result;
    }
}
