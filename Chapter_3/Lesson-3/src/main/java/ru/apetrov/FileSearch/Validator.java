package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 07.01.2017.
 */
public class Validator {

    /**
     * Массив аргументов.
     */
    private String[] args;

    /**
     * Директория.
     */
    private File dir;

    /**
     * Конструктор.
     * @param args args.
     */
    public Validator(String[] args) {
        this.args = args;
        this.dir = new File(args[1]);
    }

    /**
     * Проверка введенной комманды.
     * @return результат
     */
    private boolean checkArgs() {
        boolean result = true;
        if (!this.args[0].equals("-d")) {
            result = false;
        }

        if (!this.args[2].equals("-n")) {
            result = false;
        }

        if (!this.args[4].equals("-m") && !this.args[4].equals("-f") && !this.args[4].equals("-r")) {
            result = false;
        }

        if (!this.args[5].equals("-o")) {
            result = false;
        }
        return result;
    }

    /**
     * Выбор типа поиска.
     */
    public void isValidate() {
        if (this.checkArgs() && this.dir.isDirectory()) {

            if (this.args[4].equals("-m")) {
                System.out.println(new FindByMask(this.dir, this.args[3]));
            }

            if (this.args[4].equals("-f")) {
                System.out.println(new FindByName(this.dir, this.args[3]));
            }

            if (this.args[4].equals("-r")) {
                System.out.println(new FindByRegExp(this.dir, this.args[3]));
            }
        } else {
            System.out.printf("%s", "Ключи \n-d - директория в которая начинать поиск.\n-n - имя файл, маска, либо регулярное выражение.\n-m - искать по маске, либо -f - полное совпадение имени. -r - регулярное выражение.\n-o - результат записать в файл.");
        }
    }
}
