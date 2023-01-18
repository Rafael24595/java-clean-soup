package core.java.receiver.word;

public class DefaultNumberWordReceiver extends DefaultWordReceiver {

    protected final int MAX_ASCII = 48;
    protected final int MIN_ASCII = 58;

    @Override
    public String[] getWords() {
        return new String[]{
                "314159",
                "240595",
                "150123",
                "000000"
        };
    }

    @Override
    protected int getMaxAscii() {
        return MAX_ASCII;
    }

    @Override
    protected int getMinAscii() {
        return MIN_ASCII;
    }

}