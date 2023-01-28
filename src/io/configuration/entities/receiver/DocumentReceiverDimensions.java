package io.configuration.entities.receiver;

import core.java.module.receiver.dimensions.interfaces.IDimensionsReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;
import java.util.ArrayList;

public class DocumentReceiverDimensions extends AbstractDocumentReceiver {

    private DocumentReceiverDimensions(Document document) throws ConfigurationException {
        super(document);
        buildDimensionsReceivers();
    }

    public static DocumentReceiverDimensions getInstance(Document document) throws ConfigurationException {
        return new DocumentReceiverDimensions(document);
    }

    private void buildDimensionsReceivers() throws ConfigurationException {
        buildModule(EntityReceiverDimensions.class, EntityReceiverDimensions.DIMENSIONS_RECEIVER);
    }

    public IDimensionsReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IDimensionsReceiver> instances = super.getInstancesList();
        return instances.toArray(new IDimensionsReceiver[0]);
    }

}