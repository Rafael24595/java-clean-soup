package core.java.receiver.dimensions;

import core.java.entities.Dimensions;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;

public class CustomDimensionsReceiver implements IDimensionsReceiver {

    protected int height;
    protected int width;

    public CustomDimensionsReceiver(){
    }

    public CustomDimensionsReceiver(Integer height, Integer width){
        this.height = height;
        this.width = width;
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(height, width);
    }

}