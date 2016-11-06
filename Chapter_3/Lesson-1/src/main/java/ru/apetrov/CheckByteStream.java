package ru.apetrov;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrey on 05.11.2016.
 */
public class CheckByteStream{

    public boolean isNumber(InputStream in) throws IOException{
        boolean result = false;

        if (in.read() % 2 == 0){
            result = true;
        }
        in.close();
        return result;
    }
}
