package core.java.entities;

import core.java.print.IPrint;
import core.java.receiver.word.IWordReceiver;
import core.java.dependency.DependencyContainer;
import core.java.entities.character.WordCharacter;

import java.util.HashMap;

public class Panel {

    public static final char EMPTY_FIELD = '#';

    private Character[][] panel;
    private HashMap<String, Word> words;

    private boolean strict;

    public Panel(Dimensions dimensions, String[] words, boolean strict) throws Exception {
        this.strict = strict;
        fillPanelEmpty(dimensions);
        fillWordsList(words);
        fillEmptyFields();
    }

    public Character[] getColumn(int i) {
        return panel[i];
    }

    public char getField(int x, int y) {
        return panel[y][x];
    }
    public int getHeight() {
        return panel.length;
    }

    public int getWidth() {
        return panel[0].length;
    }

    public String[] getWords() {
        return words.keySet().toArray(new String[0]);
    }

    protected void printWord(String key) {
        Word word = words.get(key);
        WordCharacter[] characters = word.getChars();
        for (WordCharacter character: characters) {
            printCharacter(character);
        }
    }

    protected void printCharacter(WordCharacter c) {
        int x = c.getX();
        int y = c.getY();
        char character = c.getCharacter();
        setCharacter(x, y, character);
    }

    private void setCharacter(int x, int y, char character) {
        this.panel[y][x] = character;
    }

    private void fillPanelEmpty(Dimensions dimensions){
        int height = dimensions.getHeight();
        int width = dimensions.getWidth();

        this.panel = new java.lang.Character[height][width];
        for (int i = 0; i < panel.length; i++) {
            Character[] column = panel[i];
            fillColumnEmpty(column);
        }
    }

    private void fillColumnEmpty(java.lang.Character[] column) {
        for (int i = 0; i < column.length; i++) {
            column[i] = EMPTY_FIELD;
        }
    }

    private void fillWordsList(String[] wordsList) throws Exception {
        this.words = new HashMap<>();
        for (String wordString: wordsList) {
            setWord(wordString);
        }
    }

    private void setWord(String wordString) throws Exception {
        try {
            Word word = new Word(this, wordString);
            String key = word.getWord();
            this.words.put(key, word);
            printWord(key);
        }catch (Exception e){
            if (this.strict)
                throw e;
            System.out.println(e.getMessage());
        }
    }

    private void fillEmptyFields() throws Exception {
        for (int i = 0; i < panel.length; i++) {
            Character[] column = panel[i];
            fillEmptyColumn(column);
        }
    }

    private void fillEmptyColumn(Character[] column) throws Exception {
        for (int i = 0; i < column.length; i++) {
            Character character = column[i];
            if(character.equals(EMPTY_FIELD))
                column[i] = getRandomCharacter();
        }
    }

    private char getRandomCharacter() throws Exception {
        IWordReceiver receiver = DependencyContainer.getInstance(IWordReceiver.class);
        return receiver.getRandomCharacter();
    }

    public void print(IPrint printer) {
        printer.print(this);
    }

}
