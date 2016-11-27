package ru.apetrov.Chat;


import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by Andrey on 26.11.2016.
 */
public class Main {

    /**
     * Метод Main.
     * @param args args
     */
    public static void main(String[] args) {
        Chat chat = new Chat();

        InputStream inputStream = new BufferedInputStream(System.in);

        chat.runChat(inputStream);
    }
}
