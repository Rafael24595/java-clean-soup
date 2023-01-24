package core.java.dependency;

import java.util.HashMap;

import core.java.exception.DependencyException;

import static core.java.dependency.IProjectInterface.*;

public class DependencyContainer {

    private static DependencyContainer instance;
    private HashMap<String, Object> container;

    private DependencyContainer(){
        this.container = new HashMap<>();
    }

    public static void addInstance(Class<? extends IProjectInterface> interfaze, Object dependency) {
        if(instance == null)
            instance = new DependencyContainer();
        instance.container.put(interfaze.getName(), dependency);
    }

    public static <T extends IProjectInterface> T getInstance(Class<T> interfaze, Object ...args) throws DependencyException {
        return getInstance(interfaze, true, args);
    }

    public static <T extends IProjectInterface> T getInstanceDefined(Class<T> interfaze, Object ...args) throws DependencyException {
        return getInstance(interfaze, false, args);
    }

    @SuppressWarnings("unchecked")
    private static <T extends IProjectInterface> T getInstance(Class<T> interfaze, boolean def, Object ...args) throws DependencyException {
        if(instance == null)
            instance = new DependencyContainer();

        T dependency = (T) instance.container.get(interfaze.getName());

        if(def && dependency == null){
            dependency = getDefaultInstance(interfaze, args);
            addInstance(interfaze, dependency);
        }

        return dependency;
    }

    @SuppressWarnings("unchecked")
    private static <T extends IProjectInterface> T getDefaultInstance(Class<T> interfaze, Object ...args) throws DependencyException {
        Class<T> clazzDefault = null;
        try {
            clazzDefault = (Class<T>) interfaze.getField(DEF_INSTANCE).get(null);
            return clazzDefault.getDeclaredConstructor().newInstance(args);
        } catch (NoSuchFieldException e) {
            throw new DependencyException(e, "Default instance for interface \"&aux\" is undefined", interfaze.getName());
        } catch (Exception e){
            throw new DependencyException(e, "Cannot instance default class of \"&aux\" interface", interfaze.getName());
        }
    }

}