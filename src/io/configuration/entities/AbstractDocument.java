package io.configuration.entities;

import io.configuration.entities.receiver.interfaces.IModule;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import io.configuration.entities.parameter.Parameters;

public class AbstractDocument {
    
    protected Document document;

    protected AbstractDocument(Document document) {
        this.document = document;
    }

    protected void setParameters(Element element, IModule receiver) {
        Parameters.setParameters(element, receiver);
    }

}
