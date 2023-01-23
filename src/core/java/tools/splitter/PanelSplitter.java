package core.java.tools.splitter;

import core.java.entities.Area;
import core.java.entities.Position;
import core.java.entities.orientation.KOrientation;
import core.java.entities.orientation.Orientation;

import java.util.ArrayList;
import java.util.List;

public class PanelSplitter {

    public static final String SPLIT_UP_LEFT = "SPLIT_UP_LEFT";
    public static final String SPLIT_UP_RIGHT = "SPLIT_UP_RIGHT";
    public static final String SPLIT_DOWN_LEFT = "SPLIT_DOWN_LEFT";
    public static final String SPLIT_DOWN_RIGHT = "SPLIT_DOWN_RIGHT";

    public static final String NULL_WORD = "";

    protected int range;
    protected int width;
    protected int height;

    protected PanelSplitter(int range, int width , int height){
        this.range = range;
        this.width = width;
        this.height = height;
    }

    static PanelSplitter getInstance(int range, int width , int height){
        return new PanelSplitter(range, width, height);
    }

    public List<Area> splitUpLeft() throws Exception {
        return splitUpLeft(range);
    }

    ArrayList<Area> splitUpLeft(int range) throws Exception {
        return splitUpLeft(range, 0);
    }

    ArrayList<Area> splitUpLeft(int range, int fit) throws Exception {
        return splitUpLeft(range, 0, 0, fit);
    }

    ArrayList<Area> splitUpLeft(int range, int baseWidth, int baseHeight) throws Exception {
        return splitUpLeft(range, baseWidth, baseHeight, 0);
    }

    ArrayList<Area> splitUpLeft(int range, int baseWidth, int baseHeight, int fit) throws Exception {
        Orientation orientation = new Orientation(NULL_WORD, KOrientation.DIAGONAL_NORTH_WEST);

        int direction = -1;
        int fitCorrector = fit - 1;

        return buildSplit(orientation, range, baseWidth, baseHeight, direction, fitCorrector);
    }

    public List<Area> splitUpRight() throws Exception {
        return splitUpRight(range);
    }

    ArrayList<Area> splitUpRight(int range) throws Exception {
        return splitUpRight(range, 0);
    }

    ArrayList<Area> splitUpRight(int range, int fit) throws Exception {
        int baseHeight = 0;
        int baseWidth = width;

        return splitUpRight(range, baseWidth, baseHeight, fit);
    }

    ArrayList<Area> splitUpRight(int range, int baseWidth, int baseHeight) throws Exception {
        return splitUpRight(range, baseWidth, baseHeight, 0);
    }

    ArrayList<Area> splitUpRight(int range, int baseWidth, int baseHeight, int fit) throws Exception {
        Orientation orientation = new Orientation(NULL_WORD, KOrientation.DIAGONAL_NORTH_EAST);

        int direction = 1;

        return buildSplit(orientation, range, baseWidth, baseHeight, direction, fit);
    }

    public List<Area> splitDownRight() throws Exception {
        return splitDownRight(range);
    }

    ArrayList<Area> splitDownRight(int range) throws Exception {
        int baseHeight = height;
        int baseWidth = width;

        return splitUpLeft(range, baseWidth, baseHeight);
    }

    public List<Area> splitDownLeft() throws Exception {
        return splitDownLeft(range);
    }

    ArrayList<Area> splitDownLeft(int range) throws Exception {
        int baseHeight = height;
        int baseWidth = 0;

        return splitUpRight(range, baseWidth, baseHeight);
    }

    private ArrayList<Area> buildSplit(Orientation o, int range, int width, int height, int direction, int fit) throws Exception {
        ArrayList<Area> areasAux = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            int x2 = width + (range * direction * - 1) + fit + (i * direction);
            int y2 = height + i;

            Position positionB = new Position(x2, y2);
            Position positionA = getPosition(positionB, o, range);

            Area area = new Area(positionA, positionB);
            areasAux.add(area);
        }
        return areasAux;
    }

    private Position getPosition(Position startPosition, Orientation orientation, int increment) throws Exception {
        int xStart = startPosition.getX();
        int yStart = startPosition.getY();

        int xMultiplier = orientation.getHorizontalMultiplier();
        int yMultiplier = orientation.getVerticalMultiplier();

        int x = xStart + (increment * xMultiplier);
        int y = yStart + (increment * yMultiplier);

        return new Position(x, y);
    }

}