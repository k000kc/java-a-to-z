package ru.apetrov.TicTacToe.Validator;

import ru.apetrov.TicTacToe.Field.GameField;
import ru.apetrov.TicTacToe.Player.Player;

/**
 * Created by Andrey on 13.02.2017.
 */
public class CheckGame {

    private GameField gameField;

    public CheckGame(GameField gameField) {
        this.gameField = gameField;
    }

    public boolean isGameOver() {
        boolean result = false;
        if (this.isFullField()) {
            result = true;
        }
        if (this.isWinGorizontale() || this.isWinVerticale() ||
                this.isWinStraightDiagonal() || this.isWinReverseDiagonal()) {
            result = true;
            System.err.println("Game over. Found a winner");
        }
        return result;
    }

    public boolean isFullField() {
        boolean result = false;
        int count = 0;
        for (int i = 0; i < this.gameField.getFields().length; i++) {
            for (int j = 0; j < this.gameField.getFields().length; j++) {
                if (this.gameField.getFields()[i][j] == '-') {
                    count++;
                }
            }
        }
        if (count == 0) {
            result = true;
            System.err.printf("Draw");
        }
        return result;
    }

    public boolean isWinGorizontale() {
        boolean result = false;
        int count = 1;
        for (int i = 0; i < this.gameField.getFields().length; i++) {
            for (int j = 0; j < this.gameField.getFields().length - 1; j++) {
                if (this.gameField.getFields()[i][j] != '-') {
                    if (this.gameField.getFields()[i][j] == this.gameField.getFields()[i][j + 1]) {
                        count++;
                    } else {
                        count = 1;
                    }
                }
                if (count == this.gameField.getFields().length) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isWinVerticale() {
        boolean result = false;
        int count = 1;
        int j = 0;
        do {
            for (int i = 0; i < this.gameField.getFields().length - 1; i++) {
                if (this.gameField.getFields()[i][j] != '-') {
                    if (this.gameField.getFields()[i][j] == this.gameField.getFields()[i + 1][j]) {
                        count++;
                    } else {
                        count = 1;
                    }
                }
            }
            if (count == this.gameField.getFields().length) {
                result = true;
                break;
            }
            j++;
        } while (j < this.gameField.getFields().length);
        return result;
    }

    public boolean isWinStraightDiagonal() {
        boolean result =false;
        int count = 1;
        for (int i = 0; i < this.gameField.getFields().length - 1; i++) {
            if (this.gameField.getFields()[i][i] != '-') {
                if (this.gameField.getFields()[i][i] == this.gameField.getFields()[i + 1][i + 1]) {
                    count++;
                }
            }
        }
        if (count == this.gameField.getFields().length) {
            result = true;
        }
        return result;
    }

    public boolean isWinReverseDiagonal() {
        boolean result = false;
        int count = 1;
        int i = 0;
        do {
            for (int j = this.gameField.getFields().length - 1; j > 0; j--) {
                if (this.gameField.getFields()[i][i] != '-') {
                    if (this.gameField.getFields()[i][j] == this.gameField.getFields()[i + 1][j - 1]) {
                        count++;
                    }
                }
            }
            if (count == this.gameField.getFields().length) {
                result = true;
            }
            i++;
        } while (i < this.gameField.getFields().length - 1);
        return result;
    }
}
