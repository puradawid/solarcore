/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.statistics;

import java.io.File;
import java.util.Date;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import solarcore.statistics.exceptions.StatisticException;
import solarcore.therm.Thermometer;

/**
 *
 * @author Dawid
 */
public class StatLoggerXML implements StatLogger {

    Document doc = null;
    String filename;

    public StatLoggerXML(String filename) {
        this.filename = filename;
        File file = new File(filename);
        DocumentBuilder db = null;
        Element root = null;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(file);
            root = doc.getDocumentElement();

        } catch (Exception e) {
            doc = db.newDocument();
            root = doc.createElement("log");
            doc.appendChild(root);
        }

        if (root.getNodeName().equals("log")) {
            return;
        }

        doc.appendChild(doc.createElement("log"));
    }

    @Override
    public void registerAction(String action) throws StatisticException {
        Element event = doc.createElement("event");

        Element date = doc.createElement("date");
        date.setNodeValue((new Date()).toString());

        Element type = doc.createElement("action");
        type.setNodeValue(action);

        event.appendChild(type);
        event.appendChild(date);
        doc.getDocumentElement().appendChild(event);

        flush();
    }

    @Override
    public void registerTemperature(double temperature, Thermometer source) throws StatisticException {
        Element event = doc.createElement("event");

        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode((new Date()).toString()));

        Element type = doc.createElement("action");
        type.appendChild(doc.createTextNode("Temperature"));

        Element temp = doc.createElement("temperature");
        temp.appendChild(doc.createTextNode(Double.toString(temperature)));

        event.appendChild(type);
        event.appendChild(date);
        event.appendChild(temp);
    doc.getDocumentElement().appendChild(event);
        flush();
    }

    protected void flush() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (Exception e) {
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));

        // Output to console for testing
        //StreamResult result = new StreamResult(System.out);
        try {
            transformer.transform(source, result);
        } catch (Exception e) {
        }
        System.out.println("File saved!");
    }
}
