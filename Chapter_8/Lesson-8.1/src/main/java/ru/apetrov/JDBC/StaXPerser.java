package ru.apetrov.JDBC;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Andrey on 18.11.2017.
 */
public class StaXPerser {

    public void persing(File xmlFile) {
        try {
            FileInputStream stream = new FileInputStream(xmlFile);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(xmlFile.getName(), stream);
            long summa = 0;

            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    String str = reader.getAttributeValue(null, "field");
                    if (str != null) {
                        summa += Long.parseLong(str);
                    }
                }
            }
            System.out.println(summa);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
