package core.java.receiver.word;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class RAEWordReceiver extends CustomWordReceiver {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String WEB_DOMAIN = "https://dle.rae.es";
    private final String WEB_QUERY = "m=random";
    private final String NODE_REFERENCE = ".f";

    public RAEWordReceiver() throws Exception {
        this(false);
    }

    public RAEWordReceiver(Boolean dynamic) throws Exception {
        super();
        int listSize = (dynamic) ? getDynamicListSize() : DEFAULT_LIST_SIZE;
        this.words = generateWords(listSize);
    }

    public RAEWordReceiver(Integer listSize) throws Exception {
        super();
        this.words = generateWords(listSize);
    }

    public String[] generateWords(int listSize) throws Exception {
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            String word = fetchRandomWord();
            words.add(word);
        }

        return words.toArray(new String[0]);
    }

    private String fetchRandomWord() throws Exception {
        try {
            Connection connection = getConnection();
            Document document = connection.get();
            Elements elements = document.select(NODE_REFERENCE);
            Element element = elements.get(0);
            Node node = element.childNode(0);
            return cleanText(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        Connection connection = Jsoup.connect(buildUrl());
        connection.userAgent(USER_AGENT);
        return connection;
    }

    private String buildUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(WEB_DOMAIN);
        sb.append('?');
        sb.append(WEB_QUERY);
        return sb.toString();
    }

    private String cleanText(Node node) throws Exception {
        if(node instanceof TextNode){
            String text = ((TextNode) node).getWholeText();
            return text.split(",")[0];
        }
        throw new Exception("[RAE_WORD] Cannot get text content of current HTML node.");
    }

}
