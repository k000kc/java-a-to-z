package ru.apetrov;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 05.02.2017.
 */
public class SimpleGenerator implements Template {

    /**
     * Create pattern.
     */
    static final Pattern PATTERN = Pattern.compile("\\$\\{(name|subject|sos)\\}");

    /**
     * Search keys, and replacement.
     * @param template input text.
     * @param data replace word.
     * @return redy text.
     */
    public String generate(String template, Map<String, String> data) {
        Matcher matcher = PATTERN.matcher(template);
        while (matcher.find()) {
            String str = matcher.group();
            str = str.substring(2, str.length()-1);
            template = matcher.replaceFirst(data.get(str));
            matcher.reset(template);
        }
        return template;
    }
}
