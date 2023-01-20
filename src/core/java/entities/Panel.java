package core.java.entities;

import core.java.entities.character.WordCharacter;
import core.java.print.IPrint;
import core.java.receiver.dimensions.IDimensionsReceiver;
import core.java.receiver.strict.IStrictReceiver;
import core.java.receiver.word.IWordReceiver;

import java.util.HashMap;

public class Panel {

    public static final char EMPTY_FIELD = '#';

    private Character[][] table;
    private HashMap<String, Word> words;

    private IDimensionsReceiver dimensionsReceiver;
    private IWordReceiver wordsReceiver;
    private IStrictReceiver strictReceiver;

    public Panel(IDimensionsReceiver dimensionsReceiver, IWordReceiver wordsReceiver, IStrictReceiver strictReceiver) throws Exception {
        this.dimensionsReceiver = dimensionsReceiver;
        this.wordsReceiver = wordsReceiver;
        this.strictReceiver = strictReceiver;

        fillPanelEmpty();
        fillWordsList();
        fillEmptyFields();
    }

    public Character[] getColumn(int i) {
        return table[i];
    }

    public char getField(int x, int y) {
        return table[y][x];
    }
    public int getHeight() {
        return table.length;
    }

    public int getWidth() {
        return table[0].length;
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
        this.table[y][x] = character;
    }

    private void fillPanelEmpty(){
        Dimensions dimensions = this.dimensionsReceiver.getDimensions();
        int height = dimensions.getHeight();
        int width = dimensions.getWidth();

        this.table = new java.lang.Character[height][width];
        for (int i = 0; i < table.length; i++) {
            Character[] column = table[i];
            fillColumnEmpty(column);
        }
    }

    private void fillColumnEmpty(java.lang.Character[] column) {
        for (int i = 0; i < column.length; i++) {
            column[i] = EMPTY_FIELD;
        }
    }

    private void fillWordsList() throws Exception {
        String[] wordsList = this.wordsReceiver.getWords();
        this.words = new HashMap<>();
        for (String wordString: wordsList) {
            setWord(wordString);
        }
    }

    private void setWord(String wordString) throws Exception {
        boolean strict = this.strictReceiver.getStrict();
        try {
            Word word = new Word(this, wordString);
            String key = word.getString();
            this.words.put(key, word);
            printWord(key);
        }catch (Exception e){
            if (strict)
                throw e;
            System.err.println(e.getMessage());
        }
    }

    private void fillEmptyFields() {
        for (int i = 0; i < table.length; i++) {
            Character[] column = table[i];
            fillEmptyColumn(column);
        }
    }

    private void fillEmptyColumn(Character[] column) {
        for (int i = 0; i < column.length; i++) {
            Character character = column[i];
            if(character.equals(EMPTY_FIELD))
                column[i] = getRandomCharacter();
        }
    }

    private char getRandomCharacter() {
        return this.wordsReceiver.getRandomCharacter();
    }

    public void print(IPrint printer) {
        printer.print(this);
    }

}