package io.configuration.entities.parameter.interfaces;

import org.w3c.dom.Element;

public interface IParameter {
    
    String getName();

    void setName(String name);

    public String getOrder();

    public void setOrder(String order);

    String getType();

    void setType(String name);

    String getValue();

    Object getValueParsed();

    public Class<?> getClassValue();

    void setValue(String value);

    IParameter build(Element element);

}
