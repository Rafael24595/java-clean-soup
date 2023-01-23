package core.java.receiver.orientation.instance;

import core.java.receiver.IReceiver;
import core.java.receiver.orientation.DefaultOrientationReceiver;

public interface IOrientationReceiver extends IReceiver {

    Class<DefaultOrientationReceiver> def_instance = DefaultOrientationReceiver.class;
    String[] getEnabledOrientations();

}