package io.configuration.entities.receiver;

import java.util.ArrayList;

import core.java.module.receiver.word.interfaces.IWordReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;

public class DocumentReceiverWord extends AbstractDocumentReceiver {

    private DocumentReceiverWord(Document document) throws ConfigurationException {
        super(document);
        buildWordReceivers();
    }

    public static DocumentReceiverWord getInstance(Document document) throws ConfigurationException{
        return new DocumentReceiverWord(document);
    }

    private void buildWordReceivers() throws ConfigurationException {
        buildModule(EntityReceiverWord.class, EntityReceiverWord.WORD_RECEIVER);
    }

    public IWordReceiver[] getInstances() throws ConfigurationException {
        ArrayList<IWordReceiver> instances = super.getInstancesList();
        return instances.toArray(new IWordReceiver[0]);
    }

}
