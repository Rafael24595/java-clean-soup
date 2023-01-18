package core.java.receiver.dimensions;

import core.java.entities.Dimensions;

public class DefaultDimensionsReceiver implements IDimensionsReceiver {

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(11, 16);
    }

}
