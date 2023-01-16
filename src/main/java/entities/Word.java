package entities;

import entities.character.WordCharacter;
import entities.character.WordCharacters;
import entities.orientation.Orientation;

import java.text.Normalizer;

public class Word {

    private final int MAX_LOOPS = 5000;
    private Panel panel;
    private String word;

    private WordCharacters characters;
    private Orientation orientation;

    public Word(Panel panel, String word) throws Exception {
        this.panel = panel;
        this.word = normalize(word);

        this.orientation = new Orientation(this.word);
        this.characters = defineCharacters();
    }

    private String normalize(String word) {
        String normalizeWord = Normalizer.normalize(word, Normalizer.Form.NFKD);
        normalizeWord = normalizeWord.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalizeWord.toUpperCase();
    }

    public String getWord() {
        return word;
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

        throw new Exception("[WORD_EXCEPTION]: Cannot inject the word \"" + word + "\" inside the panel, insufficient free space.");
    }

    private Position getRandomStartPoint(Panel panel) throws Exception {
        int loop = 0;

        while (loop < MAX_LOOPS){
            int xProposed = (int) (Math.random() * panel.getWidth());
            int yProposed = (int) (Math.random() * panel.getHeight());
            Position position = new Position(xProposed, yProposed);

            Area area = new Area(panel, position);
            int xFinal = orientation.isHorizontalInverse() ? area.getXNegative() : area.getXPositive();
            int yFinal = orientation.isVerticalInverse() ? area.getYNegative() : area.getYPositive();

            boolean swValidArea = validArea(panel, xFinal, yFinal);

            if(swValidArea)
                return new Position(xProposed, yProposed);

            loop++;
        }

        throw new Exception("[WORD_EXCEPTION]: Cannot inject the word \"" + word + "\" inside the panel, could not set start position.");
    }

    private boolean validArea(Panel panel, int xArea, int yArea) throws Exception {
        int panelHeight = panel.getHeight();
        int panelWidth = panel.getWidth();

        int verticalLength =  orientation.getVerticalLength();
        int horizontalLength =  orientation.getHorizontalLength();

        if(panelHeight < verticalLength || panelWidth < horizontalLength)
            throw new Exception("[WORD_EXCEPTION]: Word dimensions are greater than panel dimensions.");

        return horizontalLength <= xArea && verticalLength <= yArea;
    }

}
