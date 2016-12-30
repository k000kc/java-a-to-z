package ru.apetrov.FileSearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Andrey on 30.12.2016.
 */
public class WriteLogFile {

    public void writeFile(String data) {
        try {
            File file = new File("log.txt");
            file.createNewFile();
            try(FileOutputStream inFile = new FileOutputStream(file)) {
                inFile.write(data.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
