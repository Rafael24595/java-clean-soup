package core.java.module.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.java.entities.Panel;
import core.java.entities.Word;
import core.java.entities.character.WordCharacter;
import core.java.module.print.interfaces.IPrint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserPrinter implements IPrint {

    @Override
    public String print(Panel panel) {
        try {
            return printPanel(panel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String printPanel(Panel panel) throws JsonProcessingException {
        Map<String, Object> soup = new HashMap<>();
        soup.put("panel", panel.getTable());
        List<Map<String, Object>> words = new ArrayList<>();

        for (Word word: panel.getSoupWords()) {
            Map<String, Object> oWord = new HashMap<>();
            oWord.put("word", word.getString());
            oWord.put("orientation", word.getOrientation().getCode());
            Map<String, Object> characters = new HashMap<>();
            for (WordCharacter character : word.getChars()) {
                Map<String, Object> oCharacter = new HashMap<>();
                Character c = character.getCharacter();
                int x = character.getX();
                int y = character.getY();
                oCharacter.put("character", c);
                oCharacter.put("x", x);
                oCharacter.put("y", y);
                characters.put(c + "-" + x + "-" + y, oCharacter);
            }
            oWord.put("characters", characters);
            words.add(oWord);
        }
        soup.put("words", words);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(soup);
    }

}