package ru.apetrov.FileSearch;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FindByName {
    File dir;
    String name;
    StringBuilder builder = new StringBuilder("Найденные файлы:\n");

    public FindByName(File dir, String name) {
        this.dir = dir;
        this.name = name;
    }

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
