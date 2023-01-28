package io.configuration.entities.receiver.interfaces;

import org.w3c.dom.Element;

public interface IReceiver extends IModule {

    int getOrder();

    void setOrder(int order);

    int getQuantity();

    IReceiver build(Element element);

    String getKey();

}