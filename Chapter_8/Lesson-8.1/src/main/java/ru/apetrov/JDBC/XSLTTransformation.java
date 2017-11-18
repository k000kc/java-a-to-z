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

    private String xsltFile;
    private File newXMLFile;

    public XSLTTransformation() {
        this.xsltFile = "Chapter_8\\Lesson-8.1\\src\\main\\resources\\transformer.xsl";
        this.newXMLFile = new File("Chapter_8\\Lesson-8.1\\src\\main\\resources\\2.xml");
    }

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
            System.out.printf("Файл %s создан", this.newXMLFile.getName());
        }
    }
}
