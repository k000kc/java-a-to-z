package ru.apetrov.TicTacToe.Input;

import java.util.Scanner;

/**
 * Created by Andrey on 10.02.2017.
 */
public class Input {
    
    Scanner scanner = new Scanner(System.in);

    public int ask(String message) {
        System.out.println(message);
        int result;
        if (scanner.hasNextInt()) {
            result = scanner.nextInt();
        } else {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
