package core.java.module.receiver.word;

import core.java.exception.DependencyException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class RustDictionaryWordReceiver extends CustomWordReceiver {

    private int size;
    private String domain;

    public RustDictionaryWordReceiver(Boolean dynamic, String domain) throws DependencyException, IOException {
        super();
        this.size = dynamic.booleanValue() ? getDynamicListSize() : DEFAULT_LIST_SIZE;
        this.domain = domain;
        this.words = generateWords();
    }

    public RustDictionaryWordReceiver(Integer size, String domain) throws IOException {
        super();
        this.size = size;
        this.domain = domain;
        this.words = generateWords();
    }

    public String[] generateWords() throws IOException {
        ArrayList<String> words = new ArrayList<>();

        Document response = getConnection().get();
        String body = response.body().text();
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> json = parser.parseMap(body);
        ArrayList<Map<String, Object >> results = (ArrayList<Map<String, Object>>) json.get("result");

        for (Map<String, Object > result: results) {
            Object word = result.get("word");
            words.add((String) word);
        }

        return words.toArray(new String[0]);
    }

    private Connection getConnection() {
        return Jsoup.connect(buildUrl());
    }

    private String buildUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.domain + "collection/random");
        sb.append('?');
        sb.append("size=");
        sb.append(this.size);
        return sb.toString();
    }

}