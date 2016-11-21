package ru.apetrov;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Andrey on 14.11.2016.
 */
public class MegreSort {

    /**
     * Исходный файл.
     */
    private RandomAccessFile rafSource;

    /**
     * Целевой файл.
     */
    private RandomAccessFile rafDistance;

    /**
     * Первый вспомогательноый файл.
     */
    private RandomAccessFile rafFirst;

    /**
     * Второй вспомогательноый файл.
     */
    private RandomAccessFile rafSecond;

    /**
     * Медод для сортировки исходного файла.
     * @param source исходный файл
     * @param distance целевой файл
     * @throws IOException IOException
     */
    public void sort(File source, File distance) throws IOException {
        this.rafSource = new RandomAccessFile(source, "r");
        this.rafDistance = new RandomAccessFile(distance, "rw");

        File tmpFirst = File.createTempFile("tmpFirst", ".tmp");
        File tmpSecond = File.createTempFile("tmpSecond", ".tmp");

        this.rafFirst = new RandomAccessFile(tmpFirst, "rw");
        this.rafSecond = new RandomAccessFile(tmpSecond, "rw");

        splitFile(rafSource);
        megreFile(this.rafFirst, this.rafSecond);
        while (rafSecond.length() != 0) {
            splitFile(this.rafDistance);
            megreFile(this.rafFirst, this.rafSecond);
        }
        tmpFirst.deleteOnExit();
        tmpSecond.deleteOnExit();
    }

    /**
     * Метод разбивающий исходный файл на два вспомогательных.
     * @param raf файл для разбиения
     * @throws IOException IOException
     */
    private void splitFile(RandomAccessFile raf) throws IOException {

        raf.seek(0);
        this.rafFirst.setLength(0);
        this.rafSecond.setLength(0);

        int count = 0;
        boolean splitter = true;
        String line = raf.readLine();
        while (line != null) {
            if (splitter) {
                this.rafFirst.writeBytes(String.format("%s\r\n", line));
                count = line.length();
                line = raf.readLine();
                while (line != null && count <= line.length()) {
                    this.rafFirst.writeBytes(String.format("%s\r\n", line));
                    count = line.length();
                    line = raf.readLine();
                }
                splitter = false;
            } else {
                this.rafSecond.writeBytes(String.format("%s\r\n", line));
                count = line.length();
                line = raf.readLine();
                while (line != null && count <= line.length()) {
                    this.rafSecond.writeBytes(String.format("%s\r\n", line));
                    count = line.length();
                    line = raf.readLine();
                }
                splitter = true;
            }
        }
    }

    /**
     * Метод для слияния 2х вспомогательных файлов.
     * @param first первый вспомогательный файл
     * @param second второй вспомогательный файл
     * @throws IOException IOException
     */
    private void megreFile(RandomAccessFile first, RandomAccessFile second) throws IOException {
        this.rafDistance.setLength(0);
        first.seek(0);
        second.seek(0);

        boolean stopCycleFirst = false;
        boolean stopCycleSecond = false;

        String lineFirst = first.readLine();
        String lineSecond = second.readLine();
        int countFirst = 0;
        int countSecond = 0;

        while (lineFirst != null && lineSecond != null) {

            if (lineFirst != null && lineSecond != null) {
                if (lineFirst.length() < lineSecond.length()) {
                    if (countFirst <= lineFirst.length()) {
                        this.rafDistance.writeBytes(String.format("%s\r\n", lineFirst));
                        countFirst = lineFirst.length();
                        lineFirst = first.readLine();
                    } else {
                        stopCycleFirst = true;
                    }
                } else {
                    if (countSecond <= lineSecond.length()) {
                        this.rafDistance.writeBytes(String.format("%s\r\n", lineSecond));
                        countSecond = lineSecond.length();
                        lineSecond = second.readLine();
                    } else {
                        stopCycleSecond = true;
                    }
                }
            }

            if (stopCycleFirst && !stopCycleSecond && lineSecond != null) {
                if (countSecond <= lineSecond.length()) {
                    this.rafDistance.writeBytes(String.format("%s\r\n", lineSecond));
                    countSecond = lineSecond.length();
                    lineSecond = second.readLine();
                } else {
                    stopCycleSecond = true;
                }
            }

            if (stopCycleSecond && !stopCycleFirst && lineFirst != null) {
                if (countFirst <= lineFirst.length()) {
                    this.rafDistance.writeBytes(String.format("%s\r\n", lineFirst));
                    countFirst = lineFirst.length();
                    lineFirst = first.readLine();
                } else {
                    stopCycleFirst = true;
                }
            }

            if (stopCycleFirst && stopCycleSecond) {
                stopCycleFirst = false;
                stopCycleSecond = false;
                countFirst = 0;
                countSecond = 0;
            }
        }

        while (lineFirst != null) {
            this.rafDistance.writeBytes(String.format("%s\r\n", lineFirst));
            lineFirst = first.readLine();
        }

        while (lineSecond != null) {
            this.rafDistance.writeBytes(String.format("%s\r\n", lineSecond));
            lineSecond = second.readLine();
        }
    }
}
