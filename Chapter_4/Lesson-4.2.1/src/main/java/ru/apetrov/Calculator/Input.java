package ru.apetrov.Calculator;

import java.util.Scanner;

/**
 * Created by Andrey on 05.01.2017.
 */
public class Input {

    /**
     * Система ввода.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Для ввода действий.
     * @param question вопрос.
     * @return действие.
     */
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.next();
    }

    /**
     * Для ввода аргументов.
     * @param question вопрос.
     * @return аргумент.
     */
    public double askArgument(String question) {
        double result;
        System.out.println(question);
        result = this.scanner.nextDouble();
        return result;
    }
}
