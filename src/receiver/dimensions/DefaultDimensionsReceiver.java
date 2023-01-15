package receiver.dimensions;

import entities.Dimensions;

public class DefaultDimensionsReceiver implements IDimensionsReceiver {

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(11, 16);
    }

}
