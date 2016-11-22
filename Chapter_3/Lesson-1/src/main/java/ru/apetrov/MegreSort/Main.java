package ru.apetrov.MegreSort;

import java.io.File;
import java.io.IOException;

/**
 * Created by Andrey on 19.11.2016.
 */
public class Main {

    /**
     * Main method.
     * @param args args
     * @throws IOException IOException
     */
    public static void main(String[] args) throws IOException {

        /**
         * Объявляем класс сортировки.
         */
        MegreSort sort = new MegreSort();
        File fileSource = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\SourceFile");
        File fileDistance = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\DistanceFile");
        sort.sort(fileSource, fileDistance);
    }
}
