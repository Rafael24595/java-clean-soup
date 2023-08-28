package io.configuration;

import core.java.module.log.interfaces.ILog;
import core.java.module.print.interfaces.IPrint;
import core.java.module.receiver.dimensions.interfaces.IDimensionsReceiver;
import core.java.module.receiver.orientation.interfaces.IOrientationReceiver;
import core.java.module.receiver.strict.interfaces.IStrictReceiver;
import core.java.module.receiver.word.interfaces.IWordReceiver;
import io.configuration.entities.receiver.*;
import org.w3c.dom.Document;

import io.configuration.exception.ConfigurationException;

public class Configuration {

    private static Configuration instance;

    private DocumentReceiverWord wordReceivers;
    private DocumentReceiverDimensions dimensionsReceivers;
    private DocumentReceiverStrict strictReceiver;
    private DocumentReceiverOrientation orientationReceiver;

    private DocumentSystemPrint printSystem;
    private DocumentSystemLog logSystem;

    private Configuration(Document document) throws ConfigurationException {
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
        initialize((Document) null);
    }

    public static void initialize(Document document) throws ConfigurationException {
        if(instance == null)
            instance = new Configuration(document);
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

    public static int wordReceiverLength() throws ConfigurationException {
        if(instance == null)
            initialize();
        int length = getWordReceiverInstances().length;
        return length < 1 ? 1 : length;
    }

}