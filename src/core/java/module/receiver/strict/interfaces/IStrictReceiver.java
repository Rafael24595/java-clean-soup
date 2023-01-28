package core.java.module.receiver.strict.interfaces;

import core.java.module.receiver.IReceiver;
import core.java.module.receiver.strict.DefaultStrictReceiver;

public interface IStrictReceiver extends IReceiver {

    Class<DefaultStrictReceiver> def_instance = DefaultStrictReceiver.class;

    boolean getStrict();

}
