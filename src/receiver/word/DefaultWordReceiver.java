package receiver.word;

public class DefaultWordReceiver implements IWordsReceiver {

    protected final int MAX_ASCII = 91;
    protected final int MIN_ASCII = 65;

    @Override
    public String[] getWords() {
        return new String[]{
                "Caligula",
                "Maximo",
                "Ciceron",
                "Marius",
                "Proximo",
                "Casio",
                "Vitallion"
        };
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
