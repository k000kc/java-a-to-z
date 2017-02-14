package ru.apetrov.TicTacToe.Player;

import ru.apetrov.TicTacToe.Field.GameField;

/**
 * Created by Andrey on 12.02.2017.
 */
public class Computer implements Player {

    private char symbol;

    GameField gameField;

    public Computer(char symbol, GameField gameField) {
        this.symbol = symbol;
        this.gameField = gameField;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void move() {
        int x = (int) (Math.random()*this.gameField.getFields().length);
        int y = (int) (Math.random()*this.gameField.getFields().length);

        while (this.gameField.getFields()[x][y] != '-') {
            x = (int) (Math.random()*this.gameField.getFields().length);
            y = (int) (Math.random()*this.gameField.getFields().length);
        }
        this.gameField.getFields()[x][y] = this.symbol;
    }
}
