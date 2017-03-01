package ru.apetrov.converter;

import java.util.Iterator;

/**
 * Created by Andrey on 01.03.2017.
 */
public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        Iterator<Integer> result = null;
        if (it.hasNext()) {
            result = it.next();
        }
        return result;
    }
}
