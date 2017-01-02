package ru.apetrov.Calculator;

import java.util.Scanner;

/**
 * Created by Andrey on 02.01.2017.
 */
public class InteractCalc {

    /**
     * Класс калькулятора.
     */
    private Calculator calculator = new Calculator();

    /**
     * Сканнер - система ввода.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Выбор операции.
     * @param operation операция.
     * @param first аргумент.
     * @param second аргумент.
     */
    public void checkOperation(String operation, double first, double second) {

        if (operation.equals("+")) {
            calculator.add(first, second);
        } else if (operation.equals("-")) {
            calculator.sub(first, second);
        } else if (operation.equals("*")) {
            calculator.mult(first, second);
        } else if (operation.equals("/")) {
            calculator.div(first, second);
        } else {
            System.out.println("Введена неверная операция");
        }
    }

    /**
     * Запрашиваем ввод операции.
     * @param question вопрос.
     * @return операция.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.next();
    }

    /**
     * Запрашиваем ввод аргументов.
     * @param question вопрос.
     * @return аргументы.
     */
    public double inputArg(String question) {
        System.out.println(question);
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            throw new IllegalArgumentException("Введено некорректное значение!");
        }
    }

    /**
     * Запуск программы.
     */
    public void start() {
        boolean isContinue = false;
        boolean isExit = false;
        do {
            double first;
            if (!isContinue) {
                first = inputArg("Введите первое число: ");
            } else {
                first = calculator.getResult();
            }

            String operation = ask("Введите операцию: ");
            double second = inputArg("Введите второе число: ");
            checkOperation(operation, first, second);
            System.out.printf("Результат: %s%n", calculator.getResult());
            String question = ask("Использовать результат вычислений дальше?(y/n):");
            if (question.equals("y")) {
                isContinue = true;
            } else {
                isContinue = false;
                question = ask("Выйти из программы?(y/n):");
                if (question.equals("y")) {
                    isExit = true;
                }
            }
        } while (!isExit);
    }

    /**
     * main.
     * @param args args.
     */
    public static void main(String[] args) {
        new InteractCalc().start();
    }
}
