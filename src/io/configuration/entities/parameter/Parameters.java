package io.configuration.entities.parameter;

import io.configuration.entities.parameter.interfaces.IParameter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import io.configuration.entities.receiver.interfaces.IReceiver;

import java.util.List;

import static io.configuration.tools.XmlTools.*;

public class Parameters {
    
    private Parameters(){
    }

    public static void setParameters(Element element, IReceiver receiver) {
        List<Node> parameters = getTagsElements(element, Parameter.PARAMETER);

        for (int j = 0; j < parameters.size(); j++) {
            Element paramElement = (Element) parameters.get(j);

            boolean status = getElementStatus(element);

            if(status){
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

}