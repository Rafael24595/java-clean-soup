import java.util.HashMap;

public class AbstractReceiver extends AbstractConfiguration {

    static final String DEPENDENCY = "dependency";
    static final String NAME = "name";
    static final String CLASS = "class";
    static final String PARAMETERS = "parameters";

    protected AbstractReceiver() {
        super();
    }

    String getName() {
        return getString(NAME);
    }

    void setName(String name) {
        set(NAME, name);
    }

    String getClassPath() {
        return getString(CLASS);
    }

    void setClassPath(String clazz) {
        set(CLASS, clazz);
    }

    Parameter getParameter(String field) {
        HashMap<String, Parameter> parameters = getParameters();
        return  parameters.get(field);
    }

    void setParameter(Parameter parameter) {
        HashMap<String, Parameter> parameters = getParameters();
        parameters.put(parameter.getName(), parameter);
    }

    HashMap<String, Parameter> getParameters() {
        if(!exists(PARAMETERS))
            this.container.put(PARAMETERS, new HashMap<String, Parameter>());
        return (HashMap<String, Parameter>) this.container.get(PARAMETERS);
    }

}
