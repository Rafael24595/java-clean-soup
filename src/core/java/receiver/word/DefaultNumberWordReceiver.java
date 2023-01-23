package core.java.receiver.word;

public class DefaultNumberWordReceiver extends DefaultWordReceiver {

    protected static final int MAX_ASCII = 48;
    protected static final int MIN_ASCII = 58;

    public DefaultNumberWordReceiver() {
        this.words = new String[]{
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