package ru.apetrov.JDBC;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by Andrey on 18.11.2017.
 */
public class XSLTTransformation {

    /**
     * файл шаблонов transformer.xsl.
     */
    private String xsltFile;

    /**
     * файл для записи результата.
     */
    private File newXMLFile;

    /**
     * Конструктор.
     */
    public XSLTTransformation() {
        this.xsltFile = "Chapter_8\\Lesson-8.1\\src\\main\\resources\\transformer.xsl";
        this.newXMLFile = new File("Chapter_8\\Lesson-8.1\\src\\main\\resources\\2.xml");
    }

    /**
     * Геттер.
     * @return файл для записи результата.
     */
    public File getNewXMLFile() {
        return newXMLFile;
    }

    /**
     * Запуск трансформации.
     * @param xmlFile файл над которым будет проводится трансформация.
     */
    public void runTransformation(String xmlFile) {
        try {
            StreamSource xmlSource = new StreamSource(new File(xmlFile));
            StreamSource xsltSource = new StreamSource(new File(this.xsltFile));
            StreamResult xmlResult = new StreamResult(this.newXMLFile);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsltSource);
            transformer.transform(xmlSource, xmlResult);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("Файл %s создан\n", this.newXMLFile.getName());
        }
    }
}
