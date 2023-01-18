package io.configuration.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTools {
    
    public static Element getTagElement(Document document, String tag) {
        return getTagElement(document.getDocumentElement(), tag);
    }

    public static String getTagText(Element element, String tag) {
        Element textElement = getTagElement(element, tag);
        return textElement != null ? textElement.getTextContent() : "";
    }

    public static Element getTagElement(Element element, String tag) {
        Node node = element.getElementsByTagName(tag).item(0);
        return getNodeElement(node);
    }

    public static String getTagChildText(Element element, String tag) {
        Element textElement = getTagChild(element, tag);
        return textElement != null ? textElement.getTextContent() : "";
    }

    public static Element getTagChild(Element element, String tag) {
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            Element child = getNodeElement(children.item(i));
            if(child != null &&child.getTagName().equalsIgnoreCase(tag))
                return child;
        }

        return null;
    }

    public static NodeList getTagsElements(Element element, String tag) {
        return element.getElementsByTagName(tag);
    }

    private static Element getNodeElement(Node node ) {
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element castedElement = (Element) node;
            return castedElement;
        }
        return null;
    }

}
