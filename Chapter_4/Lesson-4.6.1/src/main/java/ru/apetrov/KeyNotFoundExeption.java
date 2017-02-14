package ru.apetrov;

import java.util.IllegalFormatException;

/**
 * Created by Andrey on 14.02.2017.
 */
public class KeyNotFoundExeption extends Exception {
    public KeyNotFoundExeption(String message) {
        super(message);
    }
}
