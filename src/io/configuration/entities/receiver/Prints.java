package io.configuration.entities.receiver;

import core.java.print.IPrint;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;
import java.util.ArrayList;

public class Prints extends AbstractReceivers {

    private Prints(Document document) throws ConfigurationException {
        super(document);
        buildWordReceivers();
    }

    public static Prints getInstance(Document document) throws ConfigurationException {
        return new Prints(document);
    }

    private void buildWordReceivers() throws ConfigurationException {
        buildCollection(Print.class, Print.PRINT_RECEIVER);
    }

    public IPrint[] getInstances() throws ConfigurationException {
        ArrayList<IPrint> instances = super.getInstancesList();
        return instances.toArray(new IPrint[0]);
    }

}