package ru.apetrov.FileSearch;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Andrey on 31.12.2016.
 */
public class FileSearchTest {

    /**
     * Директория для поиска.
     */
     private String dir;

    /**
     * Лог файл.
     */
    private File pathLog;

    /**
     * Проинициализируем перед каждым тестом директорию и лог файл.
     */
    @Before
    public void init() {
        dir = "H:\\clientDir\\";
        pathLog = new File("log.txt");
        try {
            pathLog.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск по полному имени.
     */
    @Test
    public void whehFindByNameThenGetResultFile() {
        String[] input = {"-d", dir, "-n", "test.txt", "-f", "-o", "log.txt"};
        String result = "";
        FileSearch.main(input);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(pathLog)) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            result = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(result, is("H:\\clientDir\\test.txt"));
    }

    /**
     * Поиск по маске.
     */
    @Test
    public void whenFindByMaskThenGetResultFile() {
        String[] input = {"-d", dir, "-n", ".txt", "-m", "-o", "log.txt"};
        String result = "";
        FileSearch.main(input);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(pathLog)) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            result = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(result, is("H:\\clientDir\\new\\test1.txtH:\\clientDir\\test.txtH:\\clientDir\\testFile.txt"));
    }


    /**
     * Поиск по регулярному выражению.
     */
    @Test
    public void whenFindByRegExpThenGetResultFile() {
        String[] input = {"-d", dir, "-n", "test*", "-r", "-o", "log.txt"};
        String result = "";
        FileSearch.main(input);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(pathLog)) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            result = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(result, is("H:\\clientDir\\new\\test1.txtH:\\clientDir\\test.txtH:\\clientDir\\test2.jpgH:\\clientDir\\testFile.txt"));
    }
}
