package ru.apetrov;

/**
 * Created by Andrey on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        JDBCStorege storege = new JDBCStorege();
        JsoupParser parser = new JsoupParser();
        parser.parse("http://www.sql.ru/forum/job-offers");

    }
}
