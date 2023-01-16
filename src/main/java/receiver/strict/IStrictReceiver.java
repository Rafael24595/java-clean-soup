package receiver.strict;

import dependency.IProjectInterface;

public interface IStrictReceiver extends IProjectInterface {

    Class<DefaultStrictReceiver> def_instance = DefaultStrictReceiver.class;

    boolean getStrict();

}
