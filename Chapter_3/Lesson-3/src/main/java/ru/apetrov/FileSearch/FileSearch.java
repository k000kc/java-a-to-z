package ru.apetrov.FileSearch;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FileSearch {

    /**
     * Выбор типа поиска.
     * @param validator класс.
     */
    public void init(Validator validator) {
        if (validator.checkArgs() && validator.getDir().isDirectory()) {

            if (validator.getArgs()[4].equals("-m")) {
                System.out.println(new FindByMask(validator));
            }

            if (validator.getArgs()[4].equals("-f")) {
                System.out.println(new FindByName(validator));
            }

            if (validator.getArgs()[4].equals("-r")) {
                System.out.println(new FindByRegExp(validator));
            }
        } else {
            System.out.printf("%s", "Ключи \n-d - директория в которая начинать поиск.\n-n - имя файл, маска, либо регулярное выражение.\n-m - искать по маске, либо -f - полное совпадение имени. -r - регулярное выражение.\n-o - результат записать в файл.");
        }
    }

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) {
        new FileSearch().init(new Validator(args));
    }
}
