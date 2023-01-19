package io.configuration.entities.receiver.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import org.w3c.dom.Element;

import io.configuration.entities.parameter.interfaces.IParameter;

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

    <T extends core.java.receiver.IReceiver> T getInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    IReceiver build(Element element);

    String getKey();

}
