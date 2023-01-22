package core.java.tools;

public class Random {

    private java.util.Random jRandom;

    private Random(){
        this.jRandom = new java.util.Random();
    }

    public static int nextInt(int integer) {
        if(integer < 1)
            return 0;
        return new Random().jRandom.nextInt(integer);
    }

}