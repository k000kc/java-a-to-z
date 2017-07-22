package ru.apetrov.SearchFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 17.07.2017.
 */
public class Searcher {

    /**
     * Список всех файлов в заданной директории.
     */
    private static List<File> files = new ArrayList<File>();

    /**
     * входные параметры поиска (<директроия поиска> / <искомая строка>).
     */
    private final String[] args;

    /**
     * директроия поиска.
     */
    private final File dir;

    /**
     * искомая строка.
     */
    private final String line;

    /**
     * Конструктор.
     * @param args args.
     */
    public Searcher(String[] args) {
        this.args = args;
        File file = new File(args[0]);
        this.dir = file;
        this.line = args[1];
        files = Collections.synchronizedList(files);
    }

    /**
     * Геттер.
     * @return files.
     */
    public static List<File> getFiles() {
        return files;
    }

    /**
     * Запуск.
     */
    public void start() {
        this.findOfFiles(this.dir);
        this.findOfLine();
    }

    /**
     * В каждой вложенной директории создается поток,
     * добавляющий файлы в список files.
     * @param dir директроия поиска.
     */
    private void findOfFiles(File dir) {
        new Thread(new ThreadOfDirectory(dir)).start();
        for (File currentDir : dir.listFiles()) {
            if (currentDir.isDirectory()) {
                this.findOfFiles(currentDir);
            }
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Для каждого файла из списка files, создается поток поиска заданной строки в файле.
     */
    private void findOfLine() {
        for (int i = 0; i < Searcher.files.size(); i++) {
            try {
                new Thread(new ThreadOfFile(Searcher.files.get(i), this.line)).start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new Searcher(args).start();
    }
}
