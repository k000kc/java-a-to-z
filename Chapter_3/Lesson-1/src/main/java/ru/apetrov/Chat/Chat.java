package ru.apetrov.Chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Random;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Created by Andrey on 26.11.2016.
 */
public class Chat {

    /**
     * Исходный файл с фразами.
     */
    private RandomAccessFile rafSource;

    /**
     * Лог файл.
     */
    private RandomAccessFile rafLog;

    /**
     * Поставить чат на паузу.
     */
    private boolean stopChat = false;

    /**
     * Сообщение пользователя.
     */
    private String msg;

    /**
     * Случайная фраза чата.
     */
    private String phrase;

    /**
     * Размерность массива фраз.
     */
    private final int arraySize = 10;

    /**
     * Массив фраз чата.
     */
    private String[] phrases = new String[arraySize];


    /**
     * Метд для работы с чатом.
     * @param in Входящий поток
     */
    public void runChat(InputStream in) {

        File logFile = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\log");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            this.rafLog = new RandomAccessFile(logFile, "rw");
            long positionLog;

            this.msg = reader.readLine();
            while (!this.msg.equalsIgnoreCase("exit")) {

                positionLog = this.rafLog.length();
                this.rafLog.seek(positionLog);

                this.rafLog.writeBytes(String.format("%s%s\r\n", "User: ", this.msg));

                if (this.msg.equalsIgnoreCase("stop")) {
                    this.stopChat = true;
                }
                if (!stopChat) {
                    this.phrase = randomPhrase();
                    System.out.println(String.format("%s%s\r\n", "PC: ", this.phrase));
                    this.rafLog.writeBytes(String.format("%s%s\r\n", "PC: ", this.phrase));
                }
                if (this.msg.equalsIgnoreCase("start")) {
                    this.stopChat = false;
                }
                this.msg = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выбирающий случайную фразу из массива.
     * @return случайная фраза.
     * @throws IOException IOExeption
     */
    private String randomPhrase() throws IOException {
        String result = "";
        Random random = new Random();
        this.rafSource = new RandomAccessFile(new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\msg"), "r");

        int index = 0;
        String line = "";
        while ((line = this.rafSource.readLine()) != null) {
            phrases[index] = line;
            index++;
        }
        result = phrases[random.nextInt(arraySize)];
        return result;
    }
}
