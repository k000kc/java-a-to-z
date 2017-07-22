package ru.apetrov.SearchFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Andrey on 17.07.2017.
 */
public class Searcher {

    /**
     * Список всех файлов в заданной директории.
     */
    private static final BlockingQueue<File> files = new LinkedBlockingQueue<File>();

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
    }

    /**
     * Геттер.
     * @return files.
     */
    public static BlockingQueue<File> getFiles() {
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
    }

    /**
     * Для каждого файла из списка files, создается поток поиска заданной строки в файле.
     */
    private void findOfLine() {
        for (int i = 0; i < Searcher.files.size(); i++) {
            try {
                new Thread(new ThreadOfFile(Searcher.files.poll(), this.line)).start();
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
