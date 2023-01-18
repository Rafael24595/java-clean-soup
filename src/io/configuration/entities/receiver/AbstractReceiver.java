package io.configuration.entities.receiver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import core.java.receiver.word.IWordReceiver;
import io.configuration.entities.parameter.interfaces.IParameter;
import io.configuration.entities.receiver.interfaces.IReceiver;
import org.w3c.dom.Element;
import io.configuration.entities.AbstractEntity;
import static io.configuration.tools.XmlTools.*;

abstract class AbstractReceiver<T> extends AbstractEntity implements IReceiver {

    public static final String DEPENDENCY = "dependency";
    public static final String NAME = "name";
    public static final String CLASS = "class";
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
        core.java.receiver.IReceiver instance = clazz.getDeclaredConstructor(types).newInstance(args);
        return instance;
    }

    private Class<?>[] getArgsType() {
        ArrayList<Class<?>> types = new ArrayList<>();
        Set<Map.Entry<String, IParameter>> parameters = getParameters().entrySet();

        for (Map.Entry<String, IParameter> entry : parameters) {
            IParameter parameter = entry.getValue();
            Class<?> type = parameter.getClassValue();

            types.add(type);
        }

        return types.toArray(new Class[0]);
    }

    private Object[] getArgs() {
        ArrayList<Object> args = new ArrayList<>();
        Set<Map.Entry<String, IParameter>> parameters = getParameters().entrySet();

        for (Map.Entry<String, IParameter> entry : parameters) {
            IParameter parameter = entry.getValue();
            Object arg = parameter.getValueParsed();

            args.add(arg);
        }

        return args.toArray(new Object[0]);
    }

    public IReceiver build(Element element){
        String name = getTagText(element, NAME);
        String clazz = getTagText(element, CLASS);

        setName(name);
        setClassPath(clazz);
        return this;
    }

}
