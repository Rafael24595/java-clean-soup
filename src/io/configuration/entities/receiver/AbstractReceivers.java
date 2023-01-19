package io.configuration.entities.receiver;

import static io.configuration.tools.XmlTools.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import io.configuration.entities.AbstractDocument;
import io.configuration.entities.receiver.interfaces.IReceiver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        ArrayList<Node> dependencies = getTagsElements(receiverTag, AbstractReceiver.DEPENDENCY);

        for (int i = 0; i < dependencies.size(); i++) {
            Element element = (Element) dependencies.get(i);
            boolean status = getElementStatus(element);

            if(status){
                IReceiver instance = clazz.getDeclaredConstructor().newInstance();
                instance.build(element);
                setParameters(element, instance);

                this.receivers.put(instance.getKey(), instance);
            }

        }

        return this.receivers;
    }

    private String buildName(String tag) {
        Element receiverTag = getTagElement(document, tag);
        this.name = getTagChildText(receiverTag, AbstractReceiver.NAME);
        return this.name;
    }

    protected <T extends core.java.receiver.IReceiver> ArrayList<T> getInstancesList() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<T> instances = new ArrayList<>();

        for (IReceiver receiver: getSortedList()) {
            fillInstancesList(instances, receiver);
        }

        return instances;
    }

    private <T extends core.java.receiver.IReceiver> ArrayList<T> fillInstancesList(ArrayList<T> instances, IReceiver receiver) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < receiver.getQuantity(); i++) {
            T instance = receiver.getInstance();
            instances.add(instance);
        }
        return  instances;
    }

    private List<IReceiver> getSortedList() {
        Collection<IReceiver> list = receivers.values();
        return list.stream()
            .sorted(Comparator.comparingInt(IReceiver::getOrder))
            .collect(Collectors.toList());
    }

}
