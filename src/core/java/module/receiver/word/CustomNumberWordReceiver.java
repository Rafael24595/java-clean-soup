package core.java.module.receiver.word;

import core.java.exception.DependencyException;
import core.java.tools.Random;

import java.util.ArrayList;

public class CustomNumberWordReceiver extends DefaultNumberWordReceiver {

    private static final int MIN_DEFAULT = 3;

    private int max;
    private int min;

    public CustomNumberWordReceiver(Integer max, Integer min, Boolean dynamic) throws DependencyException {
        int listSize = dynamic ? getDynamicListSize() : DEFAULT_LIST_SIZE;
        this.max = (max < 1) ? 1 : max;
        this.min = (min > max) ? max : min;
        this.words = generateWords(listSize);
    }

    public CustomNumberWordReceiver(Integer listSize, Integer max) {
        this(listSize, max, MIN_DEFAULT);
    }

    public CustomNumberWordReceiver(Integer listSize, Integer max, Integer min) {
        super();
        this.max = (max < 1) ? 1 : max;
        this.min = (min > max) ? max : min;
        this.words = generateWords(listSize);
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public String[] generateWords(int listSize) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(getRandomSeries());
        }
        return list.toArray(new String[0]);
    }

    private String getRandomSeries() {
        StringBuilder sb = new StringBuilder();
        int l = getRandomLength();
        for (int i = 0; i < l; i++) {
            sb.append(getRandomNumber());
        }
        return sb.toString();
    }

    private int getRandomLength(){
        return Random.nextInt(max - min) + min;
    }

    private String getRandomNumber() {
        return String.valueOf(Random.nextInt(10));
    }

}