package core.java.module.receiver.word.interfaces;

import core.java.exception.SoupException;
import core.java.module.receiver.IReceiver;
import core.java.module.receiver.word.DefaultWordReceiver;

public interface IWordReceiver extends IReceiver {

    Class<DefaultWordReceiver> def_instance = DefaultWordReceiver.class;
    String[] getWords() throws SoupException;

    int getListSize();

    char getRandomCharacter();

}
