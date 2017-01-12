package ru.apetrov.FileSearch;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 31.12.2016.
 */
public class FindByRegExp {

    /**
     * Класс для проверки ключей.
     */
    private Validator validator;

    /**
     * Регулярное выражение.
     */
    private String regExp;

    /**
     * Собираем результат поиска.
     */
    private StringBuilder builder = new StringBuilder();

    /**
     * Конструктор.
     * @param validator класс.
     */
    public FindByRegExp(Validator validator) {
        this.validator = validator;
        this.regExp = this.validator.getArgs()[3];
    }

    /**
     * Поиск файла по регулярному выражению.
     * @return
     */
    public String find(File dir, String regExp) {
        String result;
        Pattern pattern = Pattern.compile(regExp);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, regExp);
            } else if (pattern.matcher(regExp).find()) {
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
        return String.format("%s", find(this.validator.getDir(), this.regExp));
    }
}
