package core.java.receiver.word;

import core.java.receiver.IReceiver;

public interface IWordReceiver extends IReceiver {

    Class<DefaultWordReceiver> def_instance = DefaultWordReceiver.class;
    String[] getWords() throws Exception;

    char getRandomCharacter();

}
