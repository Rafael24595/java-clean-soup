package io.configuration.entities.receiver;

import core.java.module.receiver.orientation.interfaces.IOrientationReceiver;
import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class DocumentReceiverOrientation extends AbstractDocumentReceiver {

    private DocumentReceiverOrientation(Document document) throws ConfigurationException {
        super(document);
        buildStrictReceivers();
    }

    public static DocumentReceiverOrientation getInstance(Document document) throws ConfigurationException {
        return new DocumentReceiverOrientation(document);
    }

    private void buildStrictReceivers() throws ConfigurationException {
        buildModule(EntityReceiverOrientation.class, EntityReceiverOrientation.ORIENTATION_RECEIVER);
    }

    public IOrientationReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IOrientationReceiver> instances = super.getInstancesList();
        return instances.toArray(new IOrientationReceiver[0]);
    }

}