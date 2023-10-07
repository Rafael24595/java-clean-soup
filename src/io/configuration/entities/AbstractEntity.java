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

    protected Integer[] getIntegerArray(String field){
        if(!exists(field))
            return new Integer[0];

        if(this.container.get(field) instanceof Object[]){
            Object[] array = (Object[]) this.container.get(field);
            Integer[] list = new Integer[array.length];

            for (int i = 0; i < list.length; i++) {
                list[i] = (Integer) array[i];
            }

            this.container.put(field, list);
        }

        return (Integer[]) this.container.get(field);
    }

    protected String[] getStringArray(String field){
        if(!exists(field))
            return new String[0];

        if(this.container.get(field) instanceof Object[]){
            Object[] array = (Object[]) this.container.get(field);
            String[] list = new String[array.length];

            for (int i = 0; i < list.length; i++) {
                list[i] = (String) array[i];
            }

            this.container.put(field, list);
        }

        return (String[]) this.container.get(field);
    }

    protected int getInt(String field){
        String value = getString(field);
        return exists(field) && !value.isEmpty() ? Integer.parseInt(value) : 0;
    }

    protected boolean getBoolean(String field){
        String value = getString(field);
        return exists(field) && !value.isEmpty() ? Boolean.parseBoolean(value) : false;
    }

    protected void set(String field, int value) {
        this.container.put(field, value);
    }

    protected boolean exists(String field) {
        return this.container.get(field) != null;
    }

}
