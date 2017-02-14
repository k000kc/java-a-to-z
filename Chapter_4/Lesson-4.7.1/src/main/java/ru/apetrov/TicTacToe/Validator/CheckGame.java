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
            System.out.printf("Draw");
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

//    public boolean isWinVerticale() {
//        boolean result = false;
//        int count = 1;
//        int j = 0;
//        for (int i = 0; i < this.gameField.getFields().length - 1; i++) {
//            if (this.gameField.getFields()[i][j] != '-') {
//                if (j < this.gameField.getFields().length) {
//                    if (this.gameField.getFields()[i][j] == this.gameField.getFields()[i][j + 1]) {
//                        count++;
//                        j++;
//                    }
//                }
//            }
//        }
//        return result;
//    }
}
