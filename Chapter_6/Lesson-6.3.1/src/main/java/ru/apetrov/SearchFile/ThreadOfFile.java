package ru.apetrov.SearchFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrey on 17.07.2017.
 */
public class ThreadOfFile implements Runnable {

    /**
     * Файл для поиска строки.
     */
    private final File file;

    /**
     * Искомая строка.
     */
    private final String line;

    /**
     * Поток для чтения файла.
     */
    private FileReader fReader;

    /**
     * буферизация.
     */
    private BufferedReader bReader;

    /**
     * Конструктор.
     * @param file Файл для поиска строки.
     * @param line Искомая строка.
     * @throws FileNotFoundException exeption.
     */
    public ThreadOfFile(File file, String line) throws FileNotFoundException {
        this.file = file;
        this.line = line;
        this.fReader = new FileReader(this.file);
        this.bReader = new BufferedReader(this.fReader);
    }

    /**
     * run.
     */
    public void run() {
        this.findStringOfFile();
    }

    /**
     * Поиск строки в файле.
     */
    private void findStringOfFile() {
        String currentLine = "";
        try {
            while ((currentLine = bReader.readLine()) != null) {
                if (currentLine.contains(this.line)) {
                    System.out.println(this.file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
