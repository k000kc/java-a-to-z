package ru.apetrov;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 05.02.2017.
 */
public class SimpleGeneratorTest {

    @Test
    public void whenTakeTextWithDateShouldReplacePaamsToData1() throws KeyNotFoundExeption {

        // assign
        Template template = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";
        String checket = "I am a Andrey, Who are you?";
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", "Andrey");
        data.put("subject", "you");

        // act
        String result = template.generate(text, data);

        // action
        assertThat(result, is(checket));
    }

    @Test
    public void whenTakeTextWithDateShouldReplacePaamsToData2() throws KeyNotFoundExeption {

        // assign
        Template template = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}!";
        String checket = "Help, Aaa, Aaa, Aaa!";
        Map<String, String> data = new HashMap<String, String>();
        data.put("sos", "Aaa");

        // act
        String result = template.generate(text, data);

        // action
        assertThat(result, is(checket));
    }

    @Test (expected = KeyNotFoundExeption.class)
    public void whenKeyNotFound() throws KeyNotFoundExeption {

        // assign
        Template template = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";
        String checket = "I am a Andrey, Who are you?";;
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", "Andrey");

        // act
        String result = template.generate(text, data);

        // action
        assertThat(result, is(checket));
    }
}