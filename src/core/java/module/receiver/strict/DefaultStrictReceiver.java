package core.java.module.receiver.strict;

import core.java.module.receiver.strict.interfaces.IStrictReceiver;

public class DefaultStrictReceiver implements IStrictReceiver {

    @Override
    public boolean getStrict() {
        return true;
    }

}
