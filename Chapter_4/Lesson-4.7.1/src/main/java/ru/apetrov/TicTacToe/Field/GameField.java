package ru.apetrov.TicTacToe.Field;

import ru.apetrov.TicTacToe.Field.AGameField;

/**
 * Created by Andrey on 09.02.2017.
 */
public class GameField extends AGameField {


    public GameField(int size) {
        super(size);
    }

    @Override
    public void create() {
        for (int i = 0; i < super.getFields().length; i++) {
            for (int j = 0; j < super.getFields().length; j++) {
                super.getFields()[i][j] = '-';
            }
        }
    }

    @Override
    public void showField() {
        for (int i = 0; i < super.getFields().length; i++) {
            System.out.printf("    %s", i);
        }

        for (int i = 0; i < super.getFields().length; i++) {
            System.out.printf("%n%s ", i);
            for (int j = 0; j < super.getFields().length; j++) {
                System.out.printf("[ %s ]", super.getFields()[i][j]);
            }
        }
        System.out.println();
    }
}
