package core.java.receiver.dimensions;

import core.java.tools.Random;

public class RandomSquareDimensionsReceiver extends CustomDimensionsReceiver {

    public RandomSquareDimensionsReceiver(){
    }

    public RandomSquareDimensionsReceiver(Integer minHeight, Integer maxHeight){
        this(minHeight, maxHeight, new Integer[0]);
    }

    public RandomSquareDimensionsReceiver(Integer minHeight, Integer maxHeight, Integer ...args){
        super();
        int random = getRandom(minHeight, maxHeight);
        this.height = random;
        this.width = random;
        this.areas = buildAreas(args);
    }

    public int getRandom(int min, int max) {
        return Random.nextInt(max - min) + min;
    }

}