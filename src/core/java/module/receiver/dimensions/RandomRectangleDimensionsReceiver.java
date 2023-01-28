package core.java.module.receiver.dimensions;

public class RandomRectangleDimensionsReceiver extends RandomSquareDimensionsReceiver {

    public RandomRectangleDimensionsReceiver(Integer min, Integer max){
        this(min, max, min, max, new Integer[0]);
    }

    public RandomRectangleDimensionsReceiver(Integer min, Integer max, Integer ...args){
        this(min, max, min, max, args);
    }

    public RandomRectangleDimensionsReceiver(Integer min, Integer max, String[] args, Integer range) throws Exception {
        this(min, max, min, max, args, range);
    }

    public RandomRectangleDimensionsReceiver(Integer minHeight, Integer maxHeight, Integer minWidth, Integer maxWidth){
        this(minHeight, maxHeight, minWidth, maxWidth, new Integer[0]);
    }

    public RandomRectangleDimensionsReceiver(Integer minHeight, Integer maxHeight, Integer minWidth, Integer maxWidth, Integer ...args){
        super();
        this.height = getRandom(minHeight, maxHeight);
        this.width = getRandom(minWidth, maxWidth);
        this.areas = buildAreas(args);
    }

    public RandomRectangleDimensionsReceiver(Integer minHeight, Integer maxHeight, Integer minWidth, Integer maxWidth, String[] args, Integer range) throws Exception {
        super();
        this.height = getRandom(minHeight, maxHeight);
        this.width = getRandom(minWidth, maxWidth);
        this.areas = buildAreas(args, range);
    }

}