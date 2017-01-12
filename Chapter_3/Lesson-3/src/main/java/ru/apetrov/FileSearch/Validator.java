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
     * Геттер.
     * @return args.
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Геттер.
     * @return директория.
     */
    public File getDir() {
        return dir;
    }

    /**
     * Проверка введенной комманды.
     * @return результат
     */
    public boolean checkArgs() {
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
}
