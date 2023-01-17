import java.util.HashMap;

class WordReceiver extends AbstractReceiver {

    static final String WORD_RECEIVER = "word_receiver";
    static final String QUANTITY = "quantity";

    WordReceiver() {
        super();
    }

    int getQuantity() {
        return getInt(QUANTITY);
    }

    void setQuantity(String clazz) {
        set(QUANTITY, clazz);
    }

}
