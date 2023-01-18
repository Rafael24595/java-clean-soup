package io.configuration.entities.parameter;

import io.configuration.entities.parameter.interfaces.IParameter;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import io.configuration.entities.receiver.interfaces.IReceiver;

import static io.configuration.tools.XmlTools.*;

public class Parameters {
    
    private Parameters(){
    }

    public static void setParameters(Element element, IReceiver receiver) {
        NodeList parameters = getTagsElements(element, Parameter.PARAMETER);

        for (int j = 0; j < parameters.getLength(); j++) {
            Element paramElement = (Element) parameters.item(j);

            String paramName = getTagText(paramElement, Parameter.NAME);
            String paramOrder = getTagText(paramElement, Parameter.ORDER);
            String paramType = getTagText(paramElement, Parameter.TYPE);
            String paramValue = getTagText(paramElement, Parameter.VALUE);

            IParameter parameter = new Parameter();
            parameter.setName(paramName);
            parameter.setOrder(paramOrder);
            parameter.setType(paramType);
            parameter.setValue(paramValue);

            receiver.setParameter(parameter);
        }
    }

}
