package io.configuration.entities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.configuration.entities.parameter.Parameters;
import io.configuration.entities.receiver.interfaces.IReceiver;

public class AbstractDocument {
    
    protected Document document;

    protected AbstractDocument(Document document) {
        this.document = document;
    }

    protected void setParameters(Element element, IReceiver receiver) {
        Parameters.setParameters(element, receiver);
    }

}
