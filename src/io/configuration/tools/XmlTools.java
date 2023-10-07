package io.configuration.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class XmlTools {

    private XmlTools(){
    }

    private static final String STATUS = "status";
    private static final String ENABLED = "enabled";
    private static final String DISABLED = "disabled";

    public static boolean getElementStatus(Element element) {
        if(element == null)
            return false;
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

    public static boolean existsTagChild(Element element, String tag) {
        Element child = getTagChild(element, tag);
        return child != null;
    }

    private static Element findChild(NodeList children, String tag) {
        for (int i = 0; i < children.getLength(); i++) {
            Element child = getNodeElement(children.item(i));
            if(child != null &&child.getTagName().equalsIgnoreCase(tag))
                return child;
        }

        return null;
    }

    public static List<Node> getTagsElements(Element element, String tag) {
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

    private static Element getNodeElement(Node node) {
        if(node != null && node.getNodeType() == Node.ELEMENT_NODE){
            return (Element) node;
        }
        return null;
    }

}