package io.configuration.entities.receiver;

import java.util.ArrayList;

import core.java.receiver.word.instance.IWordReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;

public class WordReceivers extends AbstractReceivers {

    private WordReceivers(Document document) throws ConfigurationException {
        super(document);
        buildWordReceivers();
    }

    public static WordReceivers getInstance(Document document) throws ConfigurationException{
        return new WordReceivers(document);
    }

    private void buildWordReceivers() throws ConfigurationException {
        buildCollection(WordReceiver.class, WordReceiver.WORD_RECEIVER);
    }

    public IWordReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IWordReceiver> instances = super.getInstancesList();
        return instances.toArray(new IWordReceiver[0]);
    }

}
