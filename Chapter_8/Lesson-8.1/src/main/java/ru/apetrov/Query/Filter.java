package ru.apetrov.Query;

/**
 * Created by Andrey on 31.10.2017.
 */
public interface Filter {

    int key();
    String execute(String field, String criterion);
}
