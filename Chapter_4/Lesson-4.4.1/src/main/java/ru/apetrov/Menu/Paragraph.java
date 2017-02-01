package ru.apetrov.Menu;

import ru.apetrov.Menu.interfaces.Action;
import ru.apetrov.Menu.interfaces.AddParagraph;
import ru.apetrov.Menu.interfaces.ShowParagraph;

import java.util.ArrayList;

/**
 * Created by Andrey on 30.01.2017.
 */
public class Paragraph implements Action, AddParagraph, ShowParagraph {

    private String name;

    private String prefix;

    private ArrayList<Paragraph> paragraphs = new ArrayList<Paragraph>(10);

    public Paragraph(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean add(Paragraph paragraph) {
        boolean result = false;
        if (paragraph != null) {
            result = this.paragraphs.add(paragraph);
        }
        return result;
    }

    public String show() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s%n", this.getPrefix(), this.getName()));
        for (Paragraph value : this.paragraphs) {
            if (value != null) {
                builder.append(String.format("%s", value.show()));
            }
        }
        String result = builder.toString();
        return result;
    }

    public void execute(String name) {
        for (Paragraph value : this.paragraphs) {
            if (value != null && value.getName().equals(name)) {
                System.out.printf("%s %s", "I work...", value.getName());
                break;
            }
        }
    }
}
