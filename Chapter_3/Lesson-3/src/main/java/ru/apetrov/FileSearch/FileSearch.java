package ru.apetrov.FileSearch;

import java.io.File;

/**
 * Created by Andrey on 25.12.2016.
 */
public class FileSearch {

    String[] args;
    File dir;

    public FileSearch(String[] args) {
        this.args = args;
    }

    public boolean isValidate(String[] args) {
        boolean result = true;
        if (!this.args[0].equals("-d")) {
            result = false;
        }

        if (!args[2].equals("-n")) {
            result = false;
        }

        if (!args[4].equals("-m") && !args[4].equals("-f") && !args[4].equals("-r")) {
            result = false;
        }

        if (!args[5].equals("-o")) {
            result = false;
        }

        return result;
    }

    public void start() {

        FileSearch fileSearch = new FileSearch(args);
        this.dir = new File(args[1]);

        if (fileSearch.isValidate(args) && this.dir.isDirectory()) {

            if (args[4].equals("-m")) {
                System.out.println(new FindByMask(this.dir, args[3]));
            }

            if (args[4].equals("-f")) {
                System.out.println(new FindByName(this.dir, args[3]));
            }

            if (args[4].equals("-r")) {
                System.out.println(new FindByRegExp(this.dir, args[3]));
            }
        } else {
            System.out.printf("%s", "Ключи \n" +
                    "-d - директория в которая начинать поиск.\n" +
                    "-n - имя файл, маска, либо регулярное выражение.\n" +
                    "-m - искать по маске, либо -f - полное совпадение имени. -r - регулярное выражение.\n" +
                    "-o - результат записать в файл.");
        }
    }

    public static void main(String[] args) {
        new FileSearch(args).start();
    }
}
