package core.java.entities;

import core.java.entities.character.WordCharacter;
import core.java.entities.character.WordCharacters;
import core.java.entities.orientation.Orientation;
import core.java.exception.ErrorCode;
import core.java.exception.SoupException;
import core.java.tools.Random;

import java.text.Normalizer;

public class Word {

    private static final int MAX_LOOPS = 5000;
    private Panel panel;
    private String string;

    private WordCharacters characters;
    private Orientation orientation;

    public Word(Panel panel, String string) throws Exception {
        this.panel = panel;
        this.string = normalize(string);

        this.orientation = new Orientation(this.string);
        this.characters = defineCharacters();
    }

    private String normalize(String word) {
        String normalizeWord = Normalizer.normalize(word, Normalizer.Form.NFKD);
        normalizeWord = normalizeWord.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalizeWord.toUpperCase();
    }

    public String getString() {
        return string;
    }

    public WordCharacter[] getChars() {
        return characters.getChars();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private WordCharacters defineCharacters() throws Exception {
        int loop = 0;

        while (loop < MAX_LOOPS){
            Position startPosition = getRandomStartPoint(panel);

            try {
                return new WordCharacters(panel, this, startPosition);
            }catch (IllegalArgumentException e){
                loop++;
            }
        }

        throw new SoupException(ErrorCode.WORD_ENTITY, "Cannot inject the word \"&var\" inside the panel, insufficient free space", string);
    }

    private Position getRandomStartPoint(Panel panel) throws SoupException {
        int loop = 0;

        while (loop < MAX_LOOPS){
            int xProposed = Random.nextInt(panel.getWidth());
            int yProposed = Random.nextInt(panel.getHeight());
            Position position = new Position(xProposed, yProposed);

            Area area = new Area(panel, position);
            int xFinal = orientation.isHorizontalInverse() ? area.getXNegative() : area.getXPositive();
            int yFinal = orientation.isVerticalInverse() ? area.getYNegative() : area.getYPositive();

            boolean swValidArea = validArea(panel, xFinal, yFinal);

            if(swValidArea)
                return new Position(xProposed, yProposed);

            loop++;
        }

        throw new SoupException(ErrorCode.WORD_ENTITY, "Cannot inject the word \"&var\" inside the panel, could not set start position.", string);
    }

    private boolean validArea(Panel panel, int xArea, int yArea) throws SoupException {
        int panelHeight = panel.getHeight();
        int panelWidth = panel.getWidth();

        int verticalLength =  orientation.getVerticalLength();
        int horizontalLength =  orientation.getHorizontalLength();

        if(panelHeight < verticalLength || panelWidth < horizontalLength)
            throw new SoupException(ErrorCode.WORD_ENTITY, "Word \"&var\" dimensions are greater than panel dimensions.", string);

        return horizontalLength <= xArea && verticalLength <= yArea;
    }

}