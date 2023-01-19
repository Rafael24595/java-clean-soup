package io.configuration.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class XmlTools {

    private final static String STATUS = "status";
    private final static String ENABLED = "enabled";
    private final static String DISABLED = "disabled";

    public static boolean getElementStatus(Element element) {
        String status = element.getAttribute(STATUS);
        return status.equals(ENABLED) || !status.equals(DISABLED);
    }

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
        Element child = null;
        if(element != null){
            NodeList children = element.getChildNodes();
            child = findChild(children, tag);
        }

        return child;
    }

    private static Element findChild(NodeList children, String tag) {
        for (int i = 0; i < children.getLength(); i++) {
            Element child = getNodeElement(children.item(i));
            if(child != null &&child.getTagName().equalsIgnoreCase(tag))
                return child;
        }

        return null;
    }

    public static ArrayList<Node> getTagsElements(Element element, String tag) {
        ArrayList<Node> aList = new ArrayList<>();
        if(element != null){
            NodeList list = element.getElementsByTagName(tag);

            for (int i = 0; i < list.getLength(); i++) {
                aList.add(list.item(i));
            }

            return aList;
        }

        return aList;
    }

    private static Element getNodeElement(Node node ) {
        if(node != null && node.getNodeType() == Node.ELEMENT_NODE){
            Element castedElement = (Element) node;
            return castedElement;
        }
        return null;
    }

}
