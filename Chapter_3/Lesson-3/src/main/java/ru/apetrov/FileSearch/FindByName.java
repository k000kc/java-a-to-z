package ru.apetrov.FileSearch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FindByName {
    File dir;
    String name;

    public FindByName(File dir, String name) {
        this.dir = dir;
        this.name = name;
    }

    public String find(File dir, String name) {
        String result = "Файл не найден";
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, name);
            } else if (name.equals(file.getName())) {
                result = "Файл найден";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s: %s\\%s", find(this.dir, this.name), Paths.get(String.valueOf(this.dir)), this.name);
    }
}
