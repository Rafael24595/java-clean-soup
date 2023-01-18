package core.java.receiver.dimensions;

import core.java.entities.Dimensions;
import core.java.receiver.IReceiver;

public interface IDimensionsReceiver extends IReceiver {

    Class<DefaultDimensionsReceiver> def_instance = DefaultDimensionsReceiver.class;
    Dimensions getDimensions();

}
