package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IReceiver;

import org.w3c.dom.Element;
import static io.configuration.tools.XmlTools.*;

abstract class AbstractEntityReceiver extends AbstractEntityModule implements IReceiver {

    public static final String DEPENDENCY = "dependency";
    public static final String ORDER = "order";

    protected AbstractEntityReceiver() {
        super();
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

    @Override
    public IReceiver build(Element element){
        super.build(element);
        String order = getTagText(element, ORDER);

        setOrder(order);
        return this;
    }

    @Override
    public String getKey() {
        String name = super.getKey();
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