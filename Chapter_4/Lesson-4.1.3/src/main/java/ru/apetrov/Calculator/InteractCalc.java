package ru.apetrov.Calculator;

import java.util.Scanner;

/**
 * Created by Andrey on 02.01.2017.
 */
public class InteractCalc {

    public static void main(String[] args) {
        MenuCalculator calculator = new MenuCalculator(new Input(), new Calculator());
        calculator.init();
    }
}
