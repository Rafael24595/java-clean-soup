package io.configuration.entities.receiver;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import io.configuration.entities.parameter.interfaces.IParameter;
import io.configuration.entities.receiver.interfaces.IReceiver;
import org.w3c.dom.Element;
import io.configuration.entities.AbstractEntity;
import static io.configuration.tools.XmlTools.*;

abstract class AbstractReceiver extends AbstractEntity implements IReceiver {

    public static final String DEPENDENCY = "dependency";
    public static final String NAME = "name";
    public static final String CLASS = "class";
    public static final String ORDER = "order";
    public static final String PARAMETERS = "parameters";

    protected AbstractReceiver() {
        super();
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public String getClassPath() {
        return getString(CLASS);
    }

    public void setClassPath(String clazz) {
        set(CLASS, clazz);
    }

    public int getOrder() {
        return getInt(ORDER);
    }

    public int getQuantity() {
        return 1;
    }

    public void setOrder(int order){
        set(ORDER, order);
    }

    public void setOrder(String order){
        set(ORDER, order);
    }

    public IParameter getParameter(String field) {
        HashMap<String, IParameter> parameters = getParameters();
        return  parameters.get(field);
    }

    public void setParameter(IParameter parameter) {
        HashMap<String, IParameter> parameters = getParameters();
        parameters.put(parameter.getName(), parameter);
    }

    public HashMap<String, IParameter> getParameters() {
        if(!exists(PARAMETERS))
            this.container.put(PARAMETERS, new HashMap<String, IParameter>());
        return (HashMap<String, IParameter>) this.container.get(PARAMETERS);
    }

    public core.java.receiver.IReceiver getInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?>[] types = getArgsType();
        Object[] args = getArgs();

        Class<core.java.receiver.IReceiver> clazz = (Class<core.java.receiver.IReceiver>) Class.forName(getClassPath());
        return clazz.getDeclaredConstructor(types).newInstance(args);
    }

    private Class<?>[] getArgsType() {
        ArrayList<Class<?>> types = new ArrayList<>();
        Collection<IParameter> parameters = getSortedParameters();

        for (IParameter parameter : parameters) {
            Class<?> type = parameter.getClassValue();

            types.add(type);
        }

        return types.toArray(new Class[0]);
    }

    private Object[] getArgs() {
        ArrayList<Object> args = new ArrayList<>();
        Collection<IParameter> parameters = getSortedParameters();

        for (IParameter parameter : parameters) {
            Object arg = parameter.getValueParsed();

            args.add(arg);
        }

        return args.toArray(new Object[0]);
    }

    private Collection<IParameter> getSortedParameters() {
        Collection<IParameter> parameters = getParameters().values();
        return parameters
                .stream()
                .sorted(Comparator.comparingInt(IParameter::getOrder))
                .collect(Collectors.toList());
    }

    public IReceiver build(Element element){
        String name = getTagText(element, NAME);
        String clazz = getTagText(element, CLASS);
        String order = getTagText(element, ORDER);

        setName(name);
        setClassPath(clazz);
        setOrder(order);
        return this;
    }

    public String getKey() {
        String name = getName();
        int order = getOrder();

        StringBuilder sb = new StringBuilder();
        sb.append(name);

        if(order != 0){
            sb.append('#');
            sb.append(order);
        }

        return sb.toString();

    }

}