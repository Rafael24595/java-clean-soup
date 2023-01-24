package io.configuration;

import core.java.print.IPrint;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;
import core.java.receiver.strict.instance.IStrictReceiver;
import core.java.receiver.word.instance.IWordReceiver;
import io.configuration.entities.receiver.DimensionsReceivers;
import io.configuration.entities.receiver.Prints;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import io.configuration.entities.receiver.StrictReceivers;
import io.configuration.entities.receiver.WordReceivers;
import io.configuration.exception.ConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Configuration {

    private static final String DEFAULT_PATH_CONFIG = "src/io/configuration/resources/Configuration.xml";

    private static Configuration instance;
    private WordReceivers wordReceivers;
    private DimensionsReceivers dimensionsReceivers;
    private StrictReceivers strictReceiver;
    private Prints printReceiver;

    private Configuration(File file) throws ConfigurationException {
        Document document = read(file);
        if(document == null)
            throw new ConfigurationException("Cannot set default settings, check provided file.");

        this.wordReceivers = WordReceivers.getInstance(document);
        this.dimensionsReceivers = DimensionsReceivers.getInstance(document);
        this.strictReceiver = StrictReceivers.getInstance(document);
        this.printReceiver = Prints.getInstance(document);
    }

    public static void initialize() throws ConfigurationException {
        initialize(null);
    }

    public static void initialize(File file) throws ConfigurationException {
        if(instance == null)
            instance = new Configuration(file);
    }

    private Document read(String path) throws ConfigurationException {
        File file = new File(path);
        try {
            return read(file);
        } catch (ConfigurationException e) {
            throw new ConfigurationException(e);
        }
    }

    private Document read(File filePro) throws ConfigurationException {
        if(filePro != null && filePro.exists()){
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            try {
                dBuilder = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(filePro);
                doc.getDocumentElement().normalize();
                return doc;

            } catch (ParserConfigurationException e) {
                throw new ConfigurationException(e);
            } catch (SAXException | IOException e){
                if(isDefaultConfigFile(filePro))
                    return null;
            }
        }

        return read(DEFAULT_PATH_CONFIG);
    }

    public static IWordReceiver getWordReceiverInstance() throws ConfigurationException {
        IWordReceiver[] receivers = getWordReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IWordReceiver[] getWordReceiverInstances() throws ConfigurationException {
        if(instance == null)
            initialize();
        return instance.wordReceivers.getInstances();
    }

    public static IDimensionsReceiver getDimensionsReceiverInstance() throws ConfigurationException {
        IDimensionsReceiver[] receivers = getDimensionsReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IDimensionsReceiver[] getDimensionsReceiverInstances() throws ConfigurationException {
        if(instance == null)
            initialize();
        return instance.dimensionsReceivers.getInstances();
    }

    public static IStrictReceiver getStrictReceiverInstance() throws ConfigurationException {
        IStrictReceiver[] receivers = getStrictReceiverInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IStrictReceiver[] getStrictReceiverInstances() throws ConfigurationException {
        if(instance == null)
            initialize();
        return instance.strictReceiver.getInstances();
    }

    public static IPrint getPrinterInstance() throws ConfigurationException {
        IPrint[] receivers = getPrinterInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IPrint[] getPrinterInstances() throws ConfigurationException {
        if(instance == null)
            initialize();
        return instance.printReceiver.getInstances();
    }

    private boolean isDefaultConfigFile(File file) {
        String fileDef = new File(DEFAULT_PATH_CONFIG).getAbsolutePath();
        String filePro = file.getAbsolutePath();
        return fileDef.equals(filePro);
    }

    public static int wordReceiverLength() throws ConfigurationException {
        if(instance == null)
            initialize();
        int length = getWordReceiverInstances().length;
        return length < 1 ? 1 : length;
    }

}