package receiver.word;

import dependency.IProjectInterface;

public interface IWordsReceiver extends IProjectInterface {

    Class<DefaultWordReceiver> def_instance = DefaultWordReceiver.class;
    String[] getWords() throws Exception;

    char getRandomCharacter();

}
