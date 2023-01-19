package core.java.receiver.dimensions;

public class RandomRectangleDimensionsReceiver extends RandomSquareDimensionsReceiver {

    public RandomRectangleDimensionsReceiver(Integer min, Integer max){
        this(min, max, min, max);
    }

    public RandomRectangleDimensionsReceiver(Integer minHeight, Integer maxHeight, Integer minWidth, Integer maxWidth){
        super();
        this.height = getRandom(minHeight, maxHeight);
        this.width = getRandom(minWidth, maxWidth);
    }



}