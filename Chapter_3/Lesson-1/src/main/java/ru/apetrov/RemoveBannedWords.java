package ru.apetrov;

import java.io.*;

/**
 * Created by Andrey on 07.11.2016.
 */
public class RemoveBannedWords {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) throws IOException {

        char count;
        char [] buff = new char[256];

        Reader reader = new InputStreamReader(in);
        Writer writer = new OutputStreamWriter(out);

        StringReader stringReader = new StringReader(reader.toString());

        while ((count = (char) reader.read(buff)) != -1){
            writer.write(count);
        }

    }
}
