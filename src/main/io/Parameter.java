public class Parameter extends AbstractConfiguration {

    static final String PARAMETER = "parameter";
    static final String NAME = "name";
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

    String getName() {
        return getString(NAME);
    }

    void setName(String name) {
        set(NAME, name);
    }

    String getType() {
        return getString(TYPE);
    }

    void setType(String name) {
        set(TYPE, name);
    }

    String getValue() {
        return getString(VALUE);
    }

    Object getValueParsed() {
        String type = TYPE;
        switch (type) {
            case Type.INTEGER:
                return getInt(VALUE);
            default:
                return getString(VALUE);
        }
    }

    void setValue(String value) {
        set(VALUE, value);
    }

}
