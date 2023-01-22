package core.java.receiver.dimensions.instance;

import core.java.entities.Dimensions;
import core.java.receiver.IReceiver;
import core.java.receiver.dimensions.DefaultDimensionsReceiver;

public interface IDimensionsReceiver extends IReceiver {

    Class<DefaultDimensionsReceiver> def_instance = DefaultDimensionsReceiver.class;
    Dimensions getDimensions();

}
