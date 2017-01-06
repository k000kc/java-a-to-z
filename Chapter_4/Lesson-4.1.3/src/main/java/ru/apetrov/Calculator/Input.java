package ru.apetrov.Calculator;

import java.util.Scanner;

/**
 * Created by Andrey on 05.01.2017.
 */
public class Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return this.scanner.next();
    }

    public double askArgument(String question) {
        double result;
        System.out.println(question);
        result = this.scanner.nextDouble();
        return result;
    }
}
