package io.configuration.entities.receiver;

import static io.configuration.tools.XmlTools.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import io.configuration.entities.AbstractDocument;
import io.configuration.entities.receiver.interfaces.IReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

abstract class AbstractReceivers extends AbstractDocument {

    private String code;
    protected HashMap<String, IReceiver> receivers;

    protected AbstractReceivers(Document document) {
        super(document);
    }

    public String getCode() {
        return code;
    }

    protected void buildCollection(Class<? extends IReceiver> clazz, String tag) throws ConfigurationException {
        buildReceivers(clazz, tag);
        buildName(tag);
    }

    private HashMap<String, IReceiver> buildReceivers(Class<? extends IReceiver> clazz, String tag) throws ConfigurationException {
        this.receivers = new HashMap<>();
        Element receiverTag = getTagElement(document, tag);
        List<Node> dependencies = getTagsElements(receiverTag, AbstractReceiver.DEPENDENCY);

        for (int i = 0; i < dependencies.size(); i++) {
            Element element = (Element) dependencies.get(i);
            boolean status = getElementStatus(element);

            if(status){
                IReceiver instance;
                try {
                    instance = clazz.getDeclaredConstructor().newInstance();
                    instance.build(element);
                    setParameters(element, instance);

                    this.receivers.put(instance.getKey(), instance);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    throw new ConfigurationException(e);
                }
            }
        }

        return this.receivers;
    }

    private String buildName(String tag) {
        Element receiverTag = getTagElement(document, tag);
        this.code = getTagChildText(receiverTag, AbstractReceiver.NAME);
        return this.code;
    }

    protected <T extends core.java.receiver.IReceiver> ArrayList<T> getInstancesList() throws ConfigurationException {
        ArrayList<T> instances = new ArrayList<>();

        for (IReceiver receiver: getSortedList()) {
            fillInstancesList(instances, receiver);
        }

        return instances;
    }

    private <T extends core.java.receiver.IReceiver> ArrayList<T> fillInstancesList(ArrayList<T> instances, IReceiver receiver) throws ConfigurationException {
        for (int i = 0; i < receiver.getQuantity(); i++) {
            T instance = receiver.getInstance();
            instances.add(instance);
        }
        return instances;
    }

    private List<IReceiver> getSortedList() {
        Collection<IReceiver> list = receivers.values();
        return list.stream()
            .sorted(Comparator.comparingInt(IReceiver::getOrder))
            .collect(Collectors.toList());
    }

}