package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FindByName {

    /**
     * Директория.
     */
    private File dir;

    /**
     * Имя файла.
     */
    private String name;

    /**
     * Собираем результат поиска.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Конструктор.
     * @param dir директория
     * @param name имя файла
     */
    public FindByName(File dir, String name) {
        this.dir = dir;
        this.name = name;
    }

    /**
     * Поиск файла по имени.
     * @param dir директория
     * @param name имя файла
     * @return Результат
     */
    public String find(File dir, String name) {
        String result;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, name);
            } else if (name.equals(file.getName())) {
                builder.append(String.format("%s\\%s%n", dir, name));
            }
        }
        result = builder.toString();
        new WriteLogFile().writeFile(result);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s", find(this.dir, this.name));
    }
}
