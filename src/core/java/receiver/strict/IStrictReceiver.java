package core.java.receiver.strict;

import core.java.receiver.IReceiver;

public interface IStrictReceiver extends IReceiver {

    Class<DefaultStrictReceiver> def_instance = DefaultStrictReceiver.class;

    boolean getStrict();

}
