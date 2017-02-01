package ru.apetrov.Menu;

import ru.apetrov.Menu.interfaces.Action;
import ru.apetrov.Menu.interfaces.AddParagraph;
import ru.apetrov.Menu.interfaces.ShowParagraph;

import java.util.ArrayList;

/**
 * Created by Andrey on 30.01.2017.
 */
public class Paragraph implements Action, AddParagraph, ShowParagraph {

    /**
     * Name.
     */
    private String name;

    /**
     * Prefix.
     */
    private String prefix;

    /**
     * Array of child.
     */
    private ArrayList<Paragraph> paragraphs = new ArrayList<Paragraph>(10);

    /**
     * Constructor.
     * @param prefix префикс.
     * @param name имя.
     */
    public Paragraph(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    /**
     * Getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Add paragraph.
     * @param paragraph paragraph.
     * @return true if add an paragraph.
     */
    public boolean add(Paragraph paragraph) {
        boolean result = false;
        if (paragraph != null) {
            result = this.paragraphs.add(paragraph);
        }
        return result;
    }

    /**
     * show all paragraphs.
     * @return paragraphs.
     */
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

    /**
     * execute.
     * @param name name an paragraph.
     */
    public void execute(String name) {
        for (Paragraph value : this.paragraphs) {
            if (value != null && value.getName().equals(name)) {
                System.out.printf("%s %s", "I work...", value.getName());
                break;
            }
        }
    }
}
