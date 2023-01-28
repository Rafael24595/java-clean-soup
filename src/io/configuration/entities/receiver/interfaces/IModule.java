package io.configuration.entities.receiver.interfaces;

import io.configuration.entities.parameter.interfaces.IParameter;
import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Element;

import java.util.HashMap;

public interface IModule {
    
    String getName();

    void setName(String name);

    String getClassPath();

    void setClassPath(String clazz);

    IParameter getParameter(String field);

    public void setParameter(IParameter parameter);

    HashMap<String, IParameter> getParameters();

    <T extends core.java.module.IModule> T getInstance() throws ConfigurationException;

    IModule build(Element element);

    String getKey();

}