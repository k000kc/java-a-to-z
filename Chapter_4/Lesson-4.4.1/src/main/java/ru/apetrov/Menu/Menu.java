package ru.apetrov.Menu;

/**
 * Created by Andrey on 30.01.2017.
 */
public class Menu {



    public static void main(String[] args) {

        Paragraph menu = new Paragraph("", "Menu:");
        Paragraph file = new Paragraph("----", "File");
        Paragraph edit = new Paragraph("----", "Edit");
        Paragraph open = new Paragraph("--------", "Open");
        Paragraph newfile = new Paragraph("--------", "New");
        Paragraph cut = new Paragraph("--------", "Cut");
        Paragraph copy = new Paragraph("--------", "Copy");
        Paragraph paste = new Paragraph("--------", "Paste");
        Paragraph view = new Paragraph("----", "View");

        menu.add(file);
        menu.add(edit);
        menu.add(view);

        file.add(newfile);
        file.add(open);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        System.out.println(menu.show());

        file.execute("Open");
    }
}
