package io.configuration.entities;

import java.util.HashMap;

public class AbstractEntity {

    protected HashMap<String, Object> container;

    protected AbstractEntity() {
        this.container = new HashMap<>();
    }

    protected Object getObject(String field){
        return exists(field) ? "" : (Object) this.container.get(field);
    }

    protected void set(String field, Object value) {
        this.container.put(field, value);
    }

    protected String getString(String field){
        return exists(field) ? String.valueOf(this.container.get(field)) : "";
    }

    protected void set(String field, String value) {
        this.container.put(field, value);
    }

    protected int getInt(String field){
        String value = getString(field);
        return exists(field) && !value.isEmpty() ? Integer.parseInt(value) : 0;
    }

    protected void set(String field, int value) {
        this.container.put(field, value);
    }

    protected boolean exists(String field) {
        return this.container.get(field) != null;
    }

}
