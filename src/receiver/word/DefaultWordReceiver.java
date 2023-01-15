package receiver.word;

public class DefaultWordReceiver implements IWordsReceiver {

    private final int MAX_ASCII = 91;
    private final int MIN_ASCII = 65;

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

    public char getRandomCharacter() {
        return (char) (Math.random() * (MAX_ASCII - MIN_ASCII) + MIN_ASCII);
    }

}
