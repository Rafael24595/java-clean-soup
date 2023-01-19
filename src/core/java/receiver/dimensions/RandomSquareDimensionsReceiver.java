package core.java.receiver.dimensions;

public class RandomSquareDimensionsReceiver extends CustomDimensionsReceiver {

    public RandomSquareDimensionsReceiver(){
    }

    public RandomSquareDimensionsReceiver(Integer minHeight, Integer maxHeight){
        super();
        int random = getRandom(minHeight, maxHeight);
        this.height = random;
        this.width = random;
    }

    public int getRandom(int min, int max) {
        return (int) ((Math.random() * max - min) + min);
    }

}