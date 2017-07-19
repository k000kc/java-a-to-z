package ru.apetrov.SearchFile;

import java.io.File;

/**
 * Created by Andrey on 17.07.2017.
 */
public class ThreadOfDirectory implements Runnable {

    /**
     * директроия поиска файлов.
     */
    private final File dir;

    /**
     * Конструктор.
     * @param dir директроия поиска файлов.
     */
    public ThreadOfDirectory(File dir) {
        this.dir = dir;
    }

    /**
     * run.
     */
    public void run() {
        this.addFindFile();
    }

    /**
     * Поиск файлов в директории.
     */
    private void addFindFile() {
        for (File file : this.dir.listFiles()) {
            if (file.isFile()) {
                Searcher.files.add(file);
            }
        }
    }
}
