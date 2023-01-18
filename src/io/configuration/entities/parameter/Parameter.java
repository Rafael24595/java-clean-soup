package io.configuration.entities.parameter;

import io.configuration.entities.parameter.interfaces.IParameter;
import org.w3c.dom.Element;
import io.configuration.entities.AbstractEntity;

import static io.configuration.tools.XmlTools.*;

class Parameter extends AbstractEntity implements IParameter {

    static final String PARAMETER = "parameter";
    static final String NAME = "name";

    static final String ORDER = "order";
    static final String TYPE = "type";
    static final String VALUE = "value";

    static class Type {

        private Type(){
        }

        static final String INTEGER = "INTEGER";

    }

    Parameter() {
        super();
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public String getOrder() {
        return getString(ORDER);
    }

    public void setOrder(String order) {
        set(ORDER, order);
    }

    public String getType() {
        return getString(TYPE);
    }

    public void setType(String name) {
        set(TYPE, name);
    }

    public String getValue() {
        return getString(VALUE);
    }

    public Object getValueParsed() {
        String type = getType();
        switch (type) {
            case Type.INTEGER:
                return getInt(VALUE);
            default:
                return getString(VALUE);
        }
    }

    public Class<?> getClassValue() {
        String type = getType();
        switch (type) {
            case Type.INTEGER:
                return Integer.class;
            default:
                return String.class;
        }
    }

    public void setValue(String value) {
        set(VALUE, value);
    }

    @Override
    public IParameter build(Element element) {
        String paramName = getTagText(element, Parameter.NAME);
        String paramType = getTagText(element, Parameter.TYPE);
        String paramValue = getTagText(element, Parameter.VALUE);

        Parameter parameter = new Parameter();
        parameter.setName(paramName);
        parameter.setType(paramType);
        parameter.setValue(paramValue);
        return this;
    }

}
