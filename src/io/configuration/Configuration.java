package io.configuration;

import core.java.print.IPrint;
import core.java.receiver.dimensions.IDimensionsReceiver;
import core.java.receiver.strict.IStrictReceiver;
import core.java.receiver.word.IWordReceiver;
import io.configuration.entities.receiver.DimensionsReceivers;
import io.configuration.entities.receiver.Prints;
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

    private static final String DEFAULT_PATH_CONFIG = "src/io/configuration/resources/Configuration.xml";

    private static Configuration instance;
    private WordReceivers wordReceivers;
    private DimensionsReceivers dimensionsReceivers;
    private StrictReceivers strictReceiver;
    private Prints printReceiver;

    private Configuration() throws IOException, ParserConfigurationException, SAXException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Document document = read();
        this.wordReceivers = WordReceivers.getInstance(document);
        this.dimensionsReceivers = DimensionsReceivers.getInstance(document);
        this.strictReceiver = StrictReceivers.getInstance(document);
        this.printReceiver = Prints.getInstance(document);
    }

    public static void initialize() throws IOException, ParserConfigurationException, SAXException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if(instance == null)
            instance = new Configuration();
    }
    private Document read() throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(DEFAULT_PATH_CONFIG);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        return doc;
    }

    public static IWordReceiver getWordReceiverInstance() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        IWordReceiver[] receivers = getWordReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IWordReceiver[] getWordReceiverInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.wordReceivers.getInstances();
    }

    public static IDimensionsReceiver getDimensionsReceiverInstance() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        IDimensionsReceiver[] receivers = getDimensionsReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IDimensionsReceiver[] getDimensionsReceiverInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.dimensionsReceivers.getInstances();
    }

    public static IStrictReceiver getStrictReceiverInstance() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        IStrictReceiver[] receivers = getStrictReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IStrictReceiver[] getStrictReceiverInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.strictReceiver.getInstances();
    }

    public static IPrint getPrinterInstance() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        IPrint[] receivers = getPrinterInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IPrint[] getPrinterInstances() throws IOException, ParserConfigurationException, InvocationTargetException, SAXException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        if(instance == null)
            initialize();
        return instance.printReceiver.getInstances();
    }

}