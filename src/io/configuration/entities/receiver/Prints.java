package io.configuration.entities.receiver;

import core.java.print.IPrint;
import org.w3c.dom.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Prints extends AbstractReceivers {

    private Prints(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        super(document);
        buildWordReceivers();
    }

    public static Prints getInstance(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException{
        return new Prints(document);
    }

    private void buildWordReceivers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        buildCollection(Print.class, Print.PRINT_RECEIVER);
    }

    public IPrint[] getInstances() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<IPrint> instances = super.getInstancesList();
        return instances.toArray(new IPrint[0]);
    }

}