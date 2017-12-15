package ru.apetrov;

import java.io.IOException;

/**
 * Created by Andrey on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        JDBCStorege storege = new JDBCStorege();
        JsoupParser parser = new JsoupParser();
        try {
            parser.loop("http://www.sql.ru/forum/job-offers");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
