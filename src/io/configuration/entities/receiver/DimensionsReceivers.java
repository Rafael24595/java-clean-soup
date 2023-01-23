package io.configuration.entities.receiver;

import core.java.receiver.dimensions.instance.IDimensionsReceiver;
import org.w3c.dom.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DimensionsReceivers extends AbstractReceivers {

    private DimensionsReceivers(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        super(document);
        buildDimensionsReceivers();
    }

    public static DimensionsReceivers getInstance(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException{
        return new DimensionsReceivers(document);
    }

    private void buildDimensionsReceivers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        buildCollection(DimensionsReceiver.class, DimensionsReceiver.DIMENSIONS_RECEIVER);
    }

    public IDimensionsReceiver[] getInstances() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<IDimensionsReceiver> instances = super.getInstancesList();
        return instances.toArray(new IDimensionsReceiver[0]);
    }

}