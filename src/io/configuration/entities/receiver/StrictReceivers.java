package io.configuration.entities.receiver;

import java.util.ArrayList;

import core.java.receiver.strict.instance.IStrictReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;

public class StrictReceivers extends AbstractReceivers {

    private StrictReceivers(Document document) throws ConfigurationException {
        super(document);
        buildStrictReceivers();
    }

    public static StrictReceivers getInstance(Document document) throws ConfigurationException {
        return new StrictReceivers(document);
    }

    private void buildStrictReceivers() throws ConfigurationException {
        buildCollection(StrictReceiver.class, StrictReceiver.STRICT_RECEIVER);
    }

    public IStrictReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IStrictReceiver> instances = super.getInstancesList();
        return instances.toArray(new IStrictReceiver[0]);
    }

}