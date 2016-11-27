package ru.apetrov.Chat;

import java.io.*;

/**
 * Created by Andrey on 26.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        Chat chat = new Chat();

        InputStream inputStream = new BufferedInputStream(System.in);

        chat.runChat(inputStream);
    }
}
