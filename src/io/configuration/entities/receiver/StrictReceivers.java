package io.configuration.entities.receiver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import core.java.receiver.strict.IStrictReceiver;
import org.w3c.dom.Document;

public class StrictReceivers extends AbstractReceivers {

    private StrictReceivers(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        super(document);
        buildStrictReceivers();
    }

    public static StrictReceivers getInstance(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException{
        return new StrictReceivers(document);
    }

    private void buildStrictReceivers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        buildCollection(StrictReceiver.class, StrictReceiver.STRICT_RECEIVER);
    }

    public IStrictReceiver[] getInstances() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<IStrictReceiver> instances = super.getInstancesList();
        return instances.toArray(new IStrictReceiver[0]);
    }

}