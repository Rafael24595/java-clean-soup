package io.configuration.entities.receiver;

import static io.configuration.tools.XmlTools.*;

import java.util.*;
import java.util.stream.Collectors;

import io.configuration.entities.receiver.interfaces.IReceiver;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

abstract class AbstractDocumentReceiver extends AbstractDocumentModule {

    private static final String INPUT= "input";

    protected HashMap<String, IReceiver> receivers;

    protected AbstractDocumentReceiver(Document document) {
        super(document);
    }

    protected void buildModule(Class<? extends IReceiver> clazz, String tag) throws ConfigurationException {
        buildDefaultModule(tag);
        buildReceivers(clazz, tag);
    }

    private HashMap<String, IReceiver> buildReceivers(Class<? extends IReceiver> clazz, String tag) throws ConfigurationException {
        this.receivers = new HashMap<>();
        Element receiverTag = getModuleTagElement(tag);
        List<Node> dependencies = getTagsElements(receiverTag, AbstractEntityReceiver.DEPENDENCY);

        for (int i = 0; i < dependencies.size(); i++) {
            IReceiver instance = buildModule(clazz, (Element) dependencies.get(i));
            if(instance != null)
                this.receivers.put(instance.getKey(), instance);
        }

        return this.receivers;
    }

    protected <T extends core.java.module.receiver.IReceiver> ArrayList<T> getInstancesList() throws ConfigurationException {
        ArrayList<T> instances = new ArrayList<>();

        for (IReceiver receiver: getSortedList()) {
            fillInstancesList(instances, receiver);
        }

        return instances;
    }

    private <T extends core.java.module.receiver.IReceiver> ArrayList<T> fillInstancesList(ArrayList<T> instances, IReceiver receiver) throws ConfigurationException {
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

    protected Element getModuleTagElement(String tag){
        Element inputTag = getTagElement(document, INPUT);
        return getTagElement(inputTag, tag);
    }

}