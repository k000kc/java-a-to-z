package ru.apetrov;

/**
 * Created by Andrey on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        JDBCStorege storege = new JDBCStorege();
        storege.add("123", "456", "789");
    }
}
