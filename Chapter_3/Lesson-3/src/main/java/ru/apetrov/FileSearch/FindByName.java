package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FindByName {

    private Validator validator;
    private String name;

    public FindByName(Validator validator) {
        this.validator = validator;
        this.name = validator.getArgs()[3];
    }

    /**
     * Собираем результат поиска.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Поиск файла по имени.
     * @return
     */
    public String find(File dir, String name) {
        String result;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, name);
            } else if (name.equals(file.getName())) {
                this.builder.append(String.format("%s\\%s%n", dir, file.getName()));
            }
        }
        result = this.builder.toString();
        new WriteLogFile().writeFile(result);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s", find(this.validator.getDir(), this.name));
    }
}
