package core.java.receiver.dimensions;

import core.java.entities.Dimensions;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;

public class DefaultDimensionsReceiver implements IDimensionsReceiver {

    public static final int DEFAULT_HEIGHT = 11;
    public static final int DEFAULT_WIDTH = 16;

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

}