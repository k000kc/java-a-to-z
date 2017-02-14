package ru.apetrov.TicTacToe.Field;

/**
 * Created by Andrey on 09.02.2017.
 */
public abstract class AGameField implements ICreateField, IPrintField{

    private int size;

    private char[][] fields;

    public AGameField(int size) {
        this.size = size;
        this.fields = new char[size][size];
        this.create();
    }

    public char[][] getFields() {
        return fields;
    }

    public void setFields(char[][] fields) {
        this.fields = fields;
    }

    public abstract void create();
    public abstract void showField();
}
