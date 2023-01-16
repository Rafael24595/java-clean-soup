package receiver.word;

import java.util.ArrayList;

public class CustomNumberWordReceiver extends DefaultNumberWordReceiver {

    private static final int MIN_LENGTH_DEFAULT = 3;

    int listSize;
    int length;
    int min;

    public CustomNumberWordReceiver(int listSize, int length) {
        this(listSize, length, MIN_LENGTH_DEFAULT);
    }

    public CustomNumberWordReceiver(int listSize, int length, int min) {
        super();
        this.listSize = listSize;
        this.length = (length < 1) ? 1 : length;
        this.min = (min > length) ? length : min;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String[] getWords() {
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
        return (int) (Math.random() * (length - min)) + min;
    }

    private String getRandomNumber() {
        return String.valueOf((int) (Math.random() * 10));
    }

}