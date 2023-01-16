package receiver.strict;

public class DisableStrictReceiver implements IStrictReceiver {

    @Override
    public boolean getStrict() {
        return false;
    }

}
