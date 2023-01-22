package core.java.receiver.strict.instance;

import core.java.receiver.IReceiver;
import core.java.receiver.strict.DefaultStrictReceiver;

public interface IStrictReceiver extends IReceiver {

    Class<DefaultStrictReceiver> def_instance = DefaultStrictReceiver.class;

    boolean getStrict();

}
