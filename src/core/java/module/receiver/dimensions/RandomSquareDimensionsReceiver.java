package core.java.module.receiver.dimensions;

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

    public RandomSquareDimensionsReceiver(Integer minHeight, Integer maxHeight, String[] args, Integer range) throws Exception {
        super();
        int random = getRandom(minHeight, maxHeight);
        this.height = random;
        this.width = random;
        this.areas = buildAreas(args, range);
    }

    public int getRandom(int min, int max) {
        return Random.nextInt(max - min) + min;
    }

}