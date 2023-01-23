package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IDimensionsReceiver;
import org.w3c.dom.Element;

import static io.configuration.tools.XmlTools.getTagText;

class DimensionsReceiver extends AbstractReceiver implements IDimensionsReceiver {

    static final String DIMENSIONS_RECEIVER = "dimensions_receiver";
    static final String QUANTITY = "quantity";

    DimensionsReceiver() {
        super();
    }

    @Override
    public int getQuantity() {
        int result = getInt(QUANTITY);
        return result == 0 ? super.getQuantity() : result;
    }

    public void setQuantity(String clazz) {
        set(QUANTITY, clazz);
    }

    @Override
    public DimensionsReceiver build(Element element){
        super.build(element);
        String quantity = getTagText(element, QUANTITY);

        setQuantity(quantity);
        return this;
    }

}