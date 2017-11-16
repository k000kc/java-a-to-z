package ru.apetrov.JDBC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Andrey on 16.11.2017.
 */
public class DOMParser {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;

    public void parsing(Integer n) throws ParserConfigurationException {
        this.factory = DocumentBuilderFactory.newInstance();
        this.builder = this.factory.newDocumentBuilder();
        this.document = this.builder.newDocument();

        Element entries = this.document.createElement("entries");
        this.document.appendChild(entries);

        Element entry = this.document.createElement("entry");
        entries.appendChild(entry);
        Element field = this.document.createElement("field");
        field.appendChild(this.document.createTextNode(n.toString()));
        entry.appendChild(field);

    }

    public void writeToXML() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(this.document);
        StreamResult streamResult = new StreamResult(new File("1.xml"));
        transformer.transform(source, streamResult);
        System.out.printf("Файл создан");
    }
}
