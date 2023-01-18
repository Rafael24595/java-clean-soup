package core.java.receiver.orientation;

import core.java.receiver.IReceiver;

public interface IOrientationReceiver extends IReceiver {

    Class<DefaultOrientationReceiver> def_instance = DefaultOrientationReceiver.class;
    String[] getEnabledOrientations();

}