package core.java.receiver.word;

import core.java.dependency.DependencyContainer;
import core.java.entities.Dimensions;
import core.java.receiver.dimensions.IDimensionsReceiver;

import static core.java.receiver.dimensions.DefaultDimensionsReceiver.*;

public class DefaultWordReceiver implements IWordReceiver {

    protected static final int MAX_ASCII = 91;
    protected static final int MIN_ASCII = 65;
    protected static final int DEFAULT_LIST_SIZE = 6;

    protected String[] words;

    public DefaultWordReceiver(){
        this.words = new String[]{
                "Caligula",
                "Maximo",
                "Ciceron",
                "Marius",
                "Proximo",
                "Casio",
                "Vitallion"
        };
    }

    @Override
    public String[] getWords() throws Exception {
        return this.words;
    }

    public int getListSize() {
        return this.words.length;
    }

    public int getDynamicListSize() throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        Dimensions dimensions = dimensionsReceiver.getDimensions();

        int multiplier = (dimensions.getHeight() * dimensions.getWidth()) / (DEFAULT_HEIGHT * DEFAULT_WIDTH);

        return DEFAULT_LIST_SIZE * multiplier;
    }

    protected int getMaxAscii() {
        return MAX_ASCII;
    }

    protected int getMinAscii() {
        return MIN_ASCII;
    }

    public char getRandomCharacter() {
        int maxAscii = getMaxAscii();
        int minAscii = getMinAscii();
        return (char) (Math.random() * (maxAscii - minAscii) + minAscii);
    }

}