package receiver.dimensions;

import dependency.IProjectInterface;
import entities.Dimensions;
import receiver.orientation.DefaultOrientationReceiver;

public interface IDimensionsReceiver extends IProjectInterface {

    Class<DefaultDimensionsReceiver> def_instance = DefaultDimensionsReceiver.class;
    Dimensions getDimensions();

}
