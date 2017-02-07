package ru.apetrov;

import java.util.Map;

/**
 * Created by Andrey on 05.02.2017.
 */
public interface Template {

    /**
     * Hello, ${name}.
     * @param template
     * @param data
     * @return
     */
    String generate(String template, Map<String, String> data);
}
