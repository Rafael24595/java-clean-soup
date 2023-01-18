package core.java.receiver.orientation;

import static entities.orientation.KOrientation.*;

public class DefaultOrientationReceiver implements IOrientationReceiver{

    @Override
    public String[] getEnabledOrientations() {
        return new String[]{
                VERTICAL_SOUTH,
                DIAGONAL_NORTH_EAST,
                HORIZONTAL_EAST,
                DIAGONAL_SOUTH_EAST,
                VERTICAL_NORTH,
                DIAGONAL_SOUTH_WEST,
                HORIZONTAL_WEST,
                DIAGONAL_NORTH_WEST
        };
    }

}
