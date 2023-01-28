package core.java.module.receiver.dimensions.interfaces;

import core.java.entities.Dimensions;
import core.java.module.receiver.IReceiver;
import core.java.module.receiver.dimensions.DefaultDimensionsReceiver;

public interface IDimensionsReceiver extends IReceiver {

    Class<DefaultDimensionsReceiver> def_instance = DefaultDimensionsReceiver.class;
    Dimensions getDimensions();

}
