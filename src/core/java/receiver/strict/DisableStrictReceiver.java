package core.java.receiver.strict;

import core.java.receiver.strict.instance.IStrictReceiver;

public class DisableStrictReceiver implements IStrictReceiver {

    @Override
    public boolean getStrict() {
        return false;
    }

}
