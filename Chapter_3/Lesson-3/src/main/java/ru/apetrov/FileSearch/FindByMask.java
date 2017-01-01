package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 30.12.2016.
 */
public class FindByMask {

    /**
     * директория.
     */
    File dir;

    /**
     * маска
     */
    String mask;

    /**
     * Собираем результат поиска.
     */
    StringBuilder builder = new StringBuilder();

    /**
     * Конструктор.
     * @param dir директория
     * @param mask маска
     */
    public FindByMask(File dir, String mask) {
        this.dir = dir;
        this.mask = mask;
    }

    /**
     * Поиск файла по маске.
     * @param dir директория
     * @param mask маска
     * @return результат
     */
    public String find(File dir, String mask) {
        String result;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, mask);
            } else if (file.getName().endsWith(mask)) {
                builder.append(String.format("%s\\%s%n", dir, file.getName()));
            }
        }
        result = builder.toString();
        new WriteLogFile().writeFile(result);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s", find(this.dir, this.mask));
    }
}
