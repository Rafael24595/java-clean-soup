package io.configuration;

import core.java.module.log.interfaces.ILog;
import core.java.module.print.interfaces.IPrint;
import core.java.module.receiver.dimensions.interfaces.IDimensionsReceiver;
import core.java.module.receiver.orientation.interfaces.IOrientationReceiver;
import core.java.module.receiver.strict.interfaces.IStrictReceiver;
import core.java.module.receiver.word.interfaces.IWordReceiver;
import io.configuration.entities.receiver.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import io.configuration.exception.ConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Configuration {

    private static final String DEFAULT_PATH_CONFIG = "src/io/configuration/resources/Configuration.xml";

    private static Configuration instance;

    private DocumentReceiverWord wordReceivers;
    private DocumentReceiverDimensions dimensionsReceivers;
    private DocumentReceiverStrict strictReceiver;
    private DocumentReceiverOrientation orientationReceiver;

    private DocumentSystemPrint printSystem;
    private DocumentSystemLog logSystem;

    private Configuration(File file) throws ConfigurationException {
        Document document = read(file);
        if(document == null)
            throw new ConfigurationException("Cannot set default settings, check provided file");

        this.wordReceivers = DocumentReceiverWord.getInstance(document);
        this.dimensionsReceivers = DocumentReceiverDimensions.getInstance(document);
        this.strictReceiver = DocumentReceiverStrict.getInstance(document);
        this.orientationReceiver = DocumentReceiverOrientation.getInstance(document);

        this.printSystem = DocumentSystemPrint.getInstance(document);
        this.logSystem = DocumentSystemLog.getInstance(document);
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

    public static IOrientationReceiver getOrientationInstance() throws ConfigurationException {
        IOrientationReceiver[] receivers = getOrientationInstances();
        if(receivers != null && receivers.length != 0)
            return receivers[0];
        return null;
    }

    public static IOrientationReceiver[] getOrientationInstances() throws ConfigurationException {
        if(instance == null)
            initialize();
        return instance.orientationReceiver.getInstances();
    }

    public static ILog getLogInstance() throws ConfigurationException {
        return instance.logSystem.getModuleInstance();
    }

    public static IPrint getPrinterInstance() throws ConfigurationException {
        return instance.printSystem.getModuleInstance();
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