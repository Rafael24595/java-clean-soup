package core.java.module.receiver.orientation.interfaces;

import core.java.module.receiver.IReceiver;
import core.java.module.receiver.orientation.DefaultOrientationReceiver;

public interface IOrientationReceiver extends IReceiver {

    Class<DefaultOrientationReceiver> def_instance = DefaultOrientationReceiver.class;
    String[] getEnabledOrientations();

}