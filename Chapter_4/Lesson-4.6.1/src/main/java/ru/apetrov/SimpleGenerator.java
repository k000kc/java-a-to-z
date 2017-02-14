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
    static final Pattern PATTERN = Pattern.compile("\\$\\{(\\w+)\\}");

    /**
     * Search keys, and replacement.
     * @param template input text.
     * @param data replace word.
     * @return redy text.
     */
    public String generate(String template, Map<String, String> data) throws KeyNotFoundExeption {
        Matcher matcher = PATTERN.matcher(template);
        while (matcher.find()) {
            String str = matcher.group(1);
            if (!data.containsKey(str)) {
                throw new KeyNotFoundExeption("Key Not Found");
            }
            template = matcher.replaceFirst(data.get(str));
            matcher.reset(template);
        }
        return template;
    }
}
