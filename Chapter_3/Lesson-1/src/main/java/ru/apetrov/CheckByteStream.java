package ru.apetrov;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrey on 05.11.2016.
 */
public class CheckByteStream {

    /**
     * Check Even or odd number.
     * @param in in
     * @return result
     * @throws IOException Exception
     */
    public boolean isNumber(InputStream in) throws IOException {
        boolean result = false;

        if (in.read() % 2 == 0) {
            result = true;
        }
        return result;
    }
}
