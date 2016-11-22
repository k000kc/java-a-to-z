package ru.apetrov.RemoveBannedWords;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Created by Andrey on 07.11.2016.
 */
public class RemoveBannedWords {

    /**
     * Class Remove Banned Words.
     * @param in in, out
     * @param out void
     * @param abuses abuses
     * @throws IOException Exception
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) throws IOException {

        int value;
        boolean isContent = false;

        Reader reader = new InputStreamReader(in);
        Writer writer = new OutputStreamWriter(out);

        StringBuilder builder = new StringBuilder();


        while ((value = reader.read()) != -1) {
            builder.append((char) value);
            for (String abuse : abuses) {
                if ((abuse.startsWith(builder.toString()))) {
                    if (builder.toString().length() == abuse.length()) {
                        builder.delete(0, builder.length());
                    } else {
                        isContent = true;
                    }
                }
            }
            if (!isContent) {
                writer.write(builder.toString());
                writer.flush();
                builder.delete(0, builder.length());
            }
            isContent = false;
        }
    }
}
