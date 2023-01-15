package receiver.orientation;

import dependency.IProjectInterface;

public interface IOrientationReceiver extends IProjectInterface {

    Class<DefaultOrientationReceiver> def_instance = DefaultOrientationReceiver.class;
    String[] getEnabledOrientations();

}