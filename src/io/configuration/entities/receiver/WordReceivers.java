package io.configuration.entities.receiver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import core.java.receiver.word.instance.IWordReceiver;
import org.w3c.dom.Document;

public class WordReceivers extends AbstractReceivers {

    private WordReceivers(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        super(document);
        buildWordReceivers();
    }

    public static WordReceivers getInstance(Document document) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException{
        return new WordReceivers(document);
    }

    private void buildWordReceivers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        buildCollection(WordReceiver.class, WordReceiver.WORD_RECEIVER);
    }

    public IWordReceiver[] getInstances() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<IWordReceiver> instances = super.getInstancesList();
        return instances.toArray(new IWordReceiver[0]);
    }

}
