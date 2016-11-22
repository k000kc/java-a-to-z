package ru.apetrov;

import org.junit.Test;
import ru.apetrov.MegreSort.MegreSort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 21.11.2016.
 */
public class MegreSortTest {

    /**
     * Объявим класс для сортировки.
     */
    private MegreSort sort = new MegreSort();

    /**
     * Запишем строку целевого файла сюда.
     */
    private StringBuilder distance = new StringBuilder();

    /**
     * Запишем строку проверочного файла сюда.
     */
    private StringBuilder result = new StringBuilder();

    /**
     * Исходный Файл.
     */
    private File fileSource = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\SourceFile");

    /**
     * Целевой файл.
     */
    private File fileDistance = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\DistanceFile");

    /**
     * Проверочный файл.
     */
    private File fileResult = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\result");

    /**
     * Проверим совпадет ли целевой файл с проверочным.
     * @throws IOException IOException
     */
    @Test
    public void whenInputFileThenSortingFile() throws IOException {
        sort.sort(fileSource, fileDistance);

        BufferedReader inResult = new BufferedReader(new FileReader(fileResult));
        String sResult;
        while ((sResult = inResult.readLine()) != null) {
            result.append(String.format("%s\r\n", sResult));
        }

        BufferedReader inDistance = new BufferedReader(new FileReader(fileDistance));
        String sDistance;
        while ((sDistance = inDistance.readLine()) != null) {
            distance.append(String.format("%s\r\n", sDistance));
        }

        assertThat(distance.toString(), is(result.toString()));
    }
}
