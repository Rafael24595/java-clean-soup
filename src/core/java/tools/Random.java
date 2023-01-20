package core.java.tools;

public class Random {

    private java.util.Random jRandom;

    private Random(){
        this.jRandom = new java.util.Random();
    }

    public static int nextInt(int integer) {
        return new Random().jRandom.nextInt(integer);
    }

}