import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Configuration {

    private static final String PATH_CONFIG = "src/main/io/Configuration.xml";

    private static Configuration instance;
    private Document document;
    private HashMap<String, WordReceiver> wordReceivers;

    private Configuration() throws IOException, ParserConfigurationException, SAXException {
        this.document = read();
        this.wordReceivers = buildWordReceivers();
    }

    public static void initialize() throws IOException, ParserConfigurationException, SAXException {
        if(instance == null)
            instance = new Configuration();
    }
    private Document read() throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(PATH_CONFIG);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        return doc;
    }

    private HashMap<String, WordReceiver> buildWordReceivers() {
        HashMap<String, WordReceiver> wordReceivers = new HashMap<>();
        Element wordReceiverTag = getTagElement(document, WordReceiver.WORD_RECEIVER);
        NodeList dependencies = getTagsElements(wordReceiverTag, WordReceiver.DEPENDENCY);

        for (int i = 0; i < dependencies.getLength(); i++) {
            Element element = (Element) dependencies.item(i);

            String name = getTagText(element, WordReceiver.NAME);
            String clazz = getTagText(element, WordReceiver.CLASS);
            String quantity = getTagText(element, WordReceiver.QUANTITY);

            WordReceiver wordReceiver = new WordReceiver();
            wordReceiver.setName(name);
            wordReceiver.setClassPath(clazz);
            wordReceiver.setQuantity(quantity);
            setParameters(element, wordReceiver);

            wordReceivers.put(name, wordReceiver);
        }

        return wordReceivers;
    }

    private void setParameters(Element element, AbstractReceiver receiver) {
        NodeList parameters = getTagsElements(element, Parameter.PARAMETER);

        for (int j = 0; j < parameters.getLength(); j++) {
            Element paramElement = (Element) parameters.item(j);

            String paramName = getTagText(paramElement, Parameter.NAME);
            String paramType = getTagText(paramElement, Parameter.TYPE);
            String paramValue = getTagText(paramElement, Parameter.VALUE);

            Parameter parameter = new Parameter();
            parameter.setName(paramName);
            parameter.setType(paramType);
            parameter.setValue(paramValue);

            receiver.setParameter(parameter);
        }
    }

    private static Element getTagElement(Document document, String tag) {
        return getTagElement(document.getDocumentElement(), tag);
    }

    private static Element getTagElement(Element element, String tag) {
        Node node = element.getElementsByTagName(tag).item(0);
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element castedElement = (Element) node;
            return castedElement;
        }
        return null;
    }

    private static String getTagText(Element element, String tag) {
        Element textElement = getTagElement(element, tag);
        return textElement != null ? textElement.getTextContent() : "";
    }

    private static NodeList getTagsElements(Element element, String tag) {
        return element.getElementsByTagName(tag);
    }

}
