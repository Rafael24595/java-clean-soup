package io.configuration.entities.receiver;

import java.util.ArrayList;

import core.java.module.receiver.strict.interfaces.IStrictReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;

public class DocumentReceiverStrict extends AbstractDocumentReceiver {

    private DocumentReceiverStrict(Document document) throws ConfigurationException {
        super(document);
        buildStrictReceivers();
    }

    public static DocumentReceiverStrict getInstance(Document document) throws ConfigurationException {
        return new DocumentReceiverStrict(document);
    }

    private void buildStrictReceivers() throws ConfigurationException {
        buildModule(EntityReceiverStrict.class, EntityReceiverStrict.STRICT_RECEIVER);
    }

    public IStrictReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IStrictReceiver> instances = super.getInstancesList();
        return instances.toArray(new IStrictReceiver[0]);
    }

}