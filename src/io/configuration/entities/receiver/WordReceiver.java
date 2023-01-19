package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IWordReceiver;
import org.w3c.dom.Element;

import static io.configuration.tools.XmlTools.*;

class WordReceiver extends AbstractReceiver implements IWordReceiver {

    static final String WORD_RECEIVER = "word_receiver";
    static final String QUANTITY = "quantity";

    WordReceiver() {
        super();
    }

    public int getQuantity() {
        return getInt(QUANTITY);
    }

    public void setQuantity(String clazz) {
        set(QUANTITY, clazz);
    }

    @Override
    public WordReceiver build(Element element){
        super.build(element);
        String quantity = getTagText(element, QUANTITY);

        setQuantity(quantity);
        return this;
    }

}
