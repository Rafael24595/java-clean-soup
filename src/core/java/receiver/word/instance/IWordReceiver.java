package core.java.receiver.word.instance;

import core.java.receiver.IReceiver;
import core.java.receiver.word.DefaultWordReceiver;

public interface IWordReceiver extends IReceiver {

    Class<DefaultWordReceiver> def_instance = DefaultWordReceiver.class;
    String[] getWords() throws Exception;

    int getListSize();

    char getRandomCharacter();

}
