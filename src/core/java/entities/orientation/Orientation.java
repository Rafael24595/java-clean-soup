package core.java.entities.orientation;

import core.java.dependency.DependencyContainer;
import core.java.receiver.orientation.instance.IOrientationReceiver;

import static core.java.entities.orientation.KOrientation.*;

public class Orientation {

    private static final int MULTIPLIER_NULL = 0;
    private static final int MULTIPLIER_DYNAMIC = 1;
    private static final int MULTIPLIER_DYNAMIC_NEGATIVE = -1;

    private String[] orientations;

    private String word;
    private String code;

    public Orientation(String word) throws Exception {
        this.word = word;
        this.orientations = generateOrientations();
        this.code = generateRandomOrientation();
    }

    private String[] generateOrientations() throws Exception {
        IOrientationReceiver orientationReceiver = DependencyContainer.getInstance(IOrientationReceiver.class);
        return orientationReceiver.getEnabledOrientations();
    }

    private String generateRandomOrientation() {
        int position = (int) (Math.random() * orientations.length);
        return orientations[position];
    }

    public int getHorizontalLength() throws Exception {
        return getHorizontalMultiplier() != MULTIPLIER_NULL ? word.length() : 1;
    }

    public int getVerticalLength() throws Exception {
        return getVerticalMultiplier() != MULTIPLIER_NULL ? word.length() : 1;
    }

    public int getHorizontalMultiplier() throws Exception {
        switch (code) {
            case DIAGONAL_SOUTH_WEST:
            case HORIZONTAL_WEST:
            case DIAGONAL_NORTH_WEST:
                return MULTIPLIER_DYNAMIC_NEGATIVE;
            case DIAGONAL_NORTH_EAST:
            case HORIZONTAL_EAST:
            case DIAGONAL_SOUTH_EAST:
                return MULTIPLIER_DYNAMIC;
            case VERTICAL_SOUTH:
            case VERTICAL_NORTH:
                return MULTIPLIER_NULL;
            default:
                throw new Exception("Orientation is not defined");
        }
    }

    public int getVerticalMultiplier() throws Exception {
        switch (code) {
            case VERTICAL_NORTH:
            case DIAGONAL_NORTH_EAST:
            case DIAGONAL_NORTH_WEST:
                return MULTIPLIER_DYNAMIC_NEGATIVE;
            case DIAGONAL_SOUTH_EAST:
            case VERTICAL_SOUTH:
            case DIAGONAL_SOUTH_WEST:
                return MULTIPLIER_DYNAMIC;
            case HORIZONTAL_EAST:
            case HORIZONTAL_WEST:
                return MULTIPLIER_NULL;
            default:
                throw new Exception("Orientation is not defined");
        }
    }

    public boolean isHorizontalInverse() throws Exception {
        return getHorizontalMultiplier() == MULTIPLIER_DYNAMIC_NEGATIVE;
    }

    public boolean isVerticalInverse() throws Exception {
        return getVerticalMultiplier() == MULTIPLIER_DYNAMIC_NEGATIVE;
    }

}