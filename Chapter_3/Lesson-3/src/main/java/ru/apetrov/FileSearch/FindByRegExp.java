package ru.apetrov.FileSearch;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 31.12.2016.
 */
public class FindByRegExp {

    /**
     * Директория.
     */
    private File dir;

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
     * @param args args.
     */
    public FindByRegExp(String[] args) {
        this.dir = new File(args[1]);
        this.regExp = args[3];
    }

    /**
     * Поиск файла по регулярному выражению.
     * @param dir директория
     * @param regExp регулярное выражение
     * @return результат
     */
    public String find(File dir, String regExp) {
        String result;
        Pattern pattern = Pattern.compile(regExp);
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                find(file, regExp);
            } else if (pattern.matcher(regExp).find()) {
                builder.append(String.format("%s\\%s%n", dir, file.getName()));
            }
        }
        result = builder.toString();
        new WriteLogFile().writeFile(result);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s", find(this.dir, this.regExp));
    }
}
