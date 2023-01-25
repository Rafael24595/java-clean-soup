package io.configuration.entities.receiver;

import core.java.receiver.dimensions.instance.IDimensionsReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;
import java.util.ArrayList;

public class DimensionsReceivers extends AbstractReceivers {

    private DimensionsReceivers(Document document) throws ConfigurationException {
        super(document);
        buildDimensionsReceivers();
    }

    public static DimensionsReceivers getInstance(Document document) throws ConfigurationException {
        return new DimensionsReceivers(document);
    }

    private void buildDimensionsReceivers() throws ConfigurationException {
        buildCollection(DimensionsReceiver.class, DimensionsReceiver.DIMENSIONS_RECEIVER);
    }

    public IDimensionsReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IDimensionsReceiver> instances = super.getInstancesList();
        return instances.toArray(new IDimensionsReceiver[0]);
    }

}