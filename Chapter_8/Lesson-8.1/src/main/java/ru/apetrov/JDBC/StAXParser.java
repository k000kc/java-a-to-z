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
public class StAXParser {

    private XMLOutputFactory factory;
    private XMLStreamWriter writer;
    private FileWriter fileWriter;
    private File file;

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

    public void closeXMLParser() {
        try {
            this.writer.writeEndElement();
            this.writer.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                this.fileWriter.close();
                System.out.printf("Файл %s создан", this.file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
