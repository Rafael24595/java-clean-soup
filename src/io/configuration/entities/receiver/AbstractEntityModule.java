package io.configuration.entities.receiver;

import io.configuration.entities.AbstractEntity;
import io.configuration.entities.parameter.interfaces.IParameter;
import io.configuration.entities.receiver.interfaces.IModule;
import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Element;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import static io.configuration.tools.XmlTools.getTagText;

abstract class AbstractEntityModule extends AbstractEntity implements IModule {

    public static final String NAME = "name";
    public static final String CLASS = "class";
    public static final String PARAMETERS = "parameters";

    protected AbstractEntityModule() {
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

    public IParameter getParameter(String field) {
        HashMap<String, IParameter> parameters = getParameters();
        return  parameters.get(field);
    }

    public void setParameter(IParameter parameter) {
        HashMap<String, IParameter> parameters = getParameters();
        parameters.put(parameter.getName(), parameter);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, IParameter> getParameters() {
        if(!exists(PARAMETERS))
            this.container.put(PARAMETERS, new HashMap<String, IParameter>());
        return (HashMap<String, IParameter>) this.container.get(PARAMETERS);
    }

    @SuppressWarnings("unchecked")
    public < T extends core.java.module.IModule> T getInstance() throws ConfigurationException {
        Class<?>[] types = getArgsType();
        Object[] args = getArgs();

        try {
            Class<T> clazz = (Class<T>) Class.forName(getClassPath());
            return clazz.getDeclaredConstructor(types).newInstance(args);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            throw new ConfigurationException(e);
        }
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

    public IModule build(Element element){
        String name = getTagText(element, NAME);
        String clazz = getTagText(element, CLASS);

        setName(name);
        setClassPath(clazz);
        return this;
    }

    public String getKey() {
        String name = getName();

        StringBuilder sb = new StringBuilder();
        sb.append(name);

        return sb.toString();
    }

}