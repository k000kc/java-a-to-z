package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FindByName {

    /**
     * Класс для проверки ключей.
     */
    private Validator validator;

    /**
     * Имя файла.
     */
    private String name;

    /**
     * Собираем результат поиска.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Констрктор.
     * @param validator класс.
     */
    public FindByName(Validator validator) {
        this.validator = validator;
        this.name = validator.getArgs()[3];
    }

    /**
     * Поиск файла по имени.
     * @param dir директория.
     * @param name имя файла.
     * @return строка с результатом.
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

    /**
     * Результат.
     * @return строка с результатом.
     */
    @Override
    public String toString() {
        return String.format("%s", find(this.validator.getDir(), this.name));
    }
}
