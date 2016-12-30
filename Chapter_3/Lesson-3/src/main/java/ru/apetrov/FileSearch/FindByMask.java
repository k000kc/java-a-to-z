package ru.apetrov.FileSearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by Andrey on 30.12.2016.
 */
public class FindByMask {

    File dir;
    String mask;
    StringBuilder builder = new StringBuilder("Найденные файлы:\n");

    public FindByMask(File dir, String mask) {
        this.dir = dir;
        this.mask = mask;
    }

    public String find(File dir, String mask) {
        String result;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, mask);
            } else if (file.getName().endsWith(mask)) {
                builder.append(String.format("%s\\%s%n", dir, mask));
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
