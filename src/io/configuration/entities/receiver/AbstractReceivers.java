package io.configuration.entities.receiver;

import static io.configuration.tools.XmlTools.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import core.java.receiver.word.IWordReceiver;
import io.configuration.entities.AbstractDocument;
import io.configuration.entities.receiver.interfaces.IReceiver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

abstract class AbstractReceivers extends AbstractDocument {

    private String name;
    protected HashMap<String, IReceiver> receivers;

    protected AbstractReceivers(Document document) {
        super(document);
    }

    protected void buildCollection(Class<? extends IReceiver> clazz, String tag) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        buildReceivers(clazz, tag);
        buildName(tag);
    }

    private HashMap<String, IReceiver> buildReceivers(Class<? extends IReceiver> clazz, String tag) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
        this.receivers = new HashMap<>();
        Element receiverTag = getTagElement(document, tag);
        NodeList dependencies = getTagsElements(receiverTag, AbstractReceiver.DEPENDENCY);

        for (int i = 0; i < dependencies.getLength(); i++) {
            Element element = (Element) dependencies.item(i);

            IReceiver instance = clazz.getDeclaredConstructor().newInstance();
            instance.build(element);
            setParameters(element, instance);

            this.receivers.put(instance.getName(), instance);
        }

        return this.receivers;
    }

    private String buildName(String tag) {
        Element receiverTag = getTagElement(document, tag);
        this.name = getTagChildText(receiverTag, AbstractReceiver.NAME);
        return this.name;
    }

    protected  <T extends core.java.receiver.IReceiver> ArrayList<T> getInstancesList() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<T> instances = new ArrayList<>();

        for (IReceiver receiver: receivers.values()) {
            T instance = receiver.getInstance();
            instances.add(instance);
        }

        return instances;
    }

}
