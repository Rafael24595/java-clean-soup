package io.configuration.entities.receiver.interfaces;

import java.util.HashMap;
import org.w3c.dom.Element;

import io.configuration.entities.parameter.interfaces.IParameter;
import io.configuration.exception.ConfigurationException;

public interface IReceiver  {
    
    String getName();

    void setName(String name);

    String getClassPath();

    void setClassPath(String clazz);

    int getOrder();

    void setOrder(int order);

    int getQuantity();

    IParameter getParameter(String field);

    public void setParameter(IParameter parameter);

    HashMap<String, IParameter> getParameters();

    <T extends core.java.receiver.IReceiver> T getInstance() throws ConfigurationException;

    IReceiver build(Element element);

    String getKey();

}