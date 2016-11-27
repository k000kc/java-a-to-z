package ru.apetrov.Chat;

import java.io.*;
import java.util.Random;

/**
 * Created by Andrey on 26.11.2016.
 */
public class Chat {

    private RandomAccessFile rafSource;
    private RandomAccessFile rafLog;
    private boolean stopChat = false;
    private String msg;
    private String phrase;
    private String[] phrases = new String[10];



    public void runChat(InputStream in) {

        File logFile = new File("H:\\projects\\java-a-to-z\\Chapter_3\\Lesson-1\\src\\main\\resources\\log");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {


            this.rafLog = new RandomAccessFile(logFile, "rw");
            long positionLog;

            while (!(this.msg = reader.readLine()).equalsIgnoreCase("exit")) {

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        result = phrases[random.nextInt(10)];
        return result;
    }
}
