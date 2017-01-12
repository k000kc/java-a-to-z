package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 30.12.2016.
 */
public class FindByMask {

    /**
     * Класс для проверки ключей.
     */
    private Validator validator;

    /**
     * Маска.
     */
    private String mask;

    /**
     * Собираем результат поиска.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Конструктор.
     * @param validator класс.
     */
    public FindByMask(Validator validator) {
        this.validator = validator;
        this.mask = validator.getArgs()[3];
    }

    /**
     * Поиск файла по маске.
     * @return
     */
    public String find(File dir, String mask) {
        String result;
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, mask);
            } else if (file.getName().endsWith(mask)) {
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
        return String.format("%s", find(this.validator.getDir(), this.mask));
    }
}
