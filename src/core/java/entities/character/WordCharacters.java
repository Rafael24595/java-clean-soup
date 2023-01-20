package core.java.entities.character;

import core.java.entities.Panel;
import core.java.entities.Position;
import core.java.entities.Word;
import core.java.entities.orientation.Orientation;

import java.util.ArrayList;

public class WordCharacters {

    private Word word;
    private ArrayList<WordCharacter> charsPositions;

    public WordCharacters(Panel panel, Word word, Position startPosition) throws Exception {
        this.word = word;
        this.charsPositions = generateCharsPosition(panel, startPosition);
    }

    private ArrayList<WordCharacter> generateCharsPosition(Panel panel, Position startPosition) throws Exception {
        ArrayList<WordCharacter> charList = new ArrayList<>();
        Orientation orientation = word.getOrientation();
        String stringWord = word.getString();

        for (int i = 0; i < stringWord.length(); i++) {
            Character character = stringWord.charAt(i);
            Position position = getPosition(startPosition, orientation, i);

            WordCharacter charPosition = new WordCharacter(position, character);
            boolean swValidChar = validCharPosition(panel, charPosition);

            if(swValidChar)
                charList.add(charPosition);
            else
                throw new IllegalArgumentException();
        }

        return charList;
    }

    private Position getPosition(Position startPosition, Orientation orientation, int increment) throws Exception {

        int xStart = startPosition.getX();
        int yStart = startPosition.getY();

        int xMultiplier = orientation.getHorizontalMultiplier();
        int yMultiplier = orientation.getVerticalMultiplier();

        int x = xStart + (increment * xMultiplier);
        int y = yStart + (increment * yMultiplier);

        return new Position(x, y);
    }

    private boolean validCharPosition(Panel panel, WordCharacter charPosition) {
        java.lang.Character panelChar = panel.getField(charPosition.getX(), charPosition.getY());
        java.lang.Character wordChar = charPosition.getCharacter();
        return panelChar.equals(Panel.EMPTY_FIELD) || wordChar.equals(panelChar);
    }

    public WordCharacter[] getChars() {
        return this.charsPositions.toArray(new WordCharacter[0]);
    }

}