package ru.apetrov;


/**
 * Created by Andrey on 14.02.2017.
 */
public class KeyNotFoundExeption extends IllegalStateException {
    public KeyNotFoundExeption(String message) {
        super(message);
    }
}
