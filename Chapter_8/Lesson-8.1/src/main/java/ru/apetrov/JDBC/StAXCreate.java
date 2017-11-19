package ru.apetrov.JDBC;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Andrey on 16.11.2017.
 */
public class StAXCreate {

    /**
     * фабрика.
     */
    private XMLOutputFactory factory;

    /**
     * поток записи.
     */
    private XMLStreamWriter writer;

    /**
     * поток записи в файл.
     */
    private FileWriter fileWriter;

    /**
     * файл.
     */
    private File file;


    /**
     * геттер.
     * @return файл.
     */
    public File getFile() {
        return file;
    }

    /**
     * Инициализация парсера.
     * @throws IOException exeption.
     */
    public void initParser() throws IOException {
        try {
            this.file = new File("Chapter_8\\Lesson-8.1\\src\\main\\resources\\1.xml");
            this.fileWriter = new FileWriter(file);
            this.factory = XMLOutputFactory.newInstance();
            this.writer = this.factory.createXMLStreamWriter(fileWriter);
            this.writer.writeStartDocument();
            this.writer.writeStartElement("entries");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * создание 1.xml файла.
     * @param count значение поля field.
     */
    public void createXML(Integer count) {
        try {
            this.writer.writeStartElement("entry");
            this.writer.writeStartElement("field");
            this.writer.writeCharacters(count.toString());
            this.writer.writeEndElement();
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * закрытие парсера.
     */
    public void closeXMLParser() {
        try {
            this.writer.writeEndElement();
            this.writer.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                this.fileWriter.close();
                System.out.printf("Файл %s создан\n", this.file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
