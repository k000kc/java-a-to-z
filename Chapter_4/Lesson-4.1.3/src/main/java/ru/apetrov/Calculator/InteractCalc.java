package ru.apetrov.Calculator;

/**
 * Created by Andrey on 02.01.2017.
 */
public class InteractCalc {

    /**
     * main.
     * @param args args.
     */
    public static void main(String[] args) {
        MenuCalculator calculator = new MenuCalculator(new Input(), new Calculator());
        calculator.init();
    }
}
