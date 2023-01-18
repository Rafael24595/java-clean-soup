package io.configuration;

import core.java.receiver.strict.IStrictReceiver;
import core.java.receiver.word.IWordReceiver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import io.configuration.entities.receiver.StrictReceivers;
import io.configuration.entities.receiver.WordReceivers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Configuration {

    private static final String PATH_CONFIG = "src/io/configuration/resources/Configuration.xml";

    private static Configuration instance;
    private WordReceivers wordReceivers;
    private StrictReceivers strictReceiver;

    private Configuration() throws IOException, ParserConfigurationException, SAXException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Document document = read();
        this.wordReceivers = WordReceivers.getInstance(document);
        this.strictReceiver = StrictReceivers.getInstance(document);
    }

    public static void initialize() throws IOException, ParserConfigurationException, SAXException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if(instance == null)
            instance = new Configuration();
    }
    private Document read() throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(PATH_CONFIG);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public static IWordReceiver[] getWordReceiverInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.wordReceivers.getInstances();
    }

    public static IStrictReceiver[] getStrictReceiverInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.strictReceiver.getInstances();
    }

}
