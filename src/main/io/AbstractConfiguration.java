import java.util.HashMap;

public class AbstractConfiguration {

    protected HashMap<String, Object> container;

    protected AbstractConfiguration() {
        this.container = new HashMap<>();
    }

    Object getObject(String field){
        return exists(field) ? "" : (Object) this.container.get(field);
    }

    void set(String field, Object value) {
        this.container.put(field, value);
    }

    String getString(String field){
        return exists(field) ? (String) this.container.get(field) : "";
    }

    void set(String field, String value) {
        this.container.put(field, value);
    }

    int getInt(String field){
        return exists(field) ? (int) this.container.get(field) : 0;
    }

    void set(String field, int value) {
        this.container.put(field, value);
    }

    boolean exists(String field) {
        return this.container.get(field) != null;
    }

}
