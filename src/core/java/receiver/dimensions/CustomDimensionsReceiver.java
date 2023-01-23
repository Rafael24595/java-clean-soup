package core.java.receiver.dimensions;

import core.java.entities.Area;
import core.java.entities.Dimensions;
import core.java.entities.Position;
import core.java.entities.orientation.KOrientation;
import core.java.entities.orientation.Orientation;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;

import java.util.ArrayList;

public class CustomDimensionsReceiver implements IDimensionsReceiver {

    protected static final String SPLIT_UP_LEFT = "SPLIT_UP_LEFT";
    protected static final String SPLIT_UP_RIGHT = "SPLIT_UP_RIGHT";
    protected static final String SPLIT_DOWN_LEFT = "SPLIT_DOWN_LEFT";
    protected static final String SPLIT_DOWN_RIGHT = "SPLIT_DOWN_RIGHT";

    protected int height;
    protected int width;
    protected Area[] areas;

    public CustomDimensionsReceiver(){
    }

    public CustomDimensionsReceiver(Integer height, Integer width){
        this(height, width, new Integer[0]);
    }

    public CustomDimensionsReceiver(Integer height, Integer width, Integer[] args){
        this.height = height;
        this.width = width;
        this.areas = buildAreas(args);
    }

    public CustomDimensionsReceiver(Integer height, Integer width, String[] args, Integer range) throws Exception {
        this.height = height;
        this.width = width;
        this.areas = buildAreas(args, range);
    }

    protected Area[] buildAreas(Integer ...args) {
        ArrayList<Area> areasAux = new ArrayList<>();
        int loops = args.length / 4;
        for (int i = 0; i < loops; i++) {
            int xA = args[0 + i * 4];
            int yA = args[1 + i * 4];
            int xB = args[2 + i * 4];
            int yB = args[3 + i * 4];

            Position positionA = new Position(xA, yA);
            Position positionB = new Position(xB, yB);

            areasAux.add(new Area(positionA, positionB));
        }

        return areasAux.toArray(new Area[0]);
    }

    protected Area[] buildAreas(String[] args, int range) throws Exception {
        ArrayList<Area> areasAux = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case SPLIT_UP_LEFT:
                    areasAux.addAll(splitUpLeft(range));
                    break;
                case SPLIT_UP_RIGHT:
                    areasAux.addAll(splitUpRight(range));
                    break;
                case SPLIT_DOWN_LEFT:
                    areasAux.addAll(splitDownLeft(range));
                    break;
                case SPLIT_DOWN_RIGHT:
                    areasAux.addAll(splitDownRight(range));
                    break;
                default:
                    break;
            }
        }

        return areasAux.toArray(new Area[0]);
    }

    private ArrayList<Area> splitUpLeft(int range) throws Exception {
        Orientation orientation = new Orientation("", KOrientation.DIAGONAL_NORTH_WEST);

        int baseHeight = 0;
        int baseWidth = 0;
        int direction = -1;
        int fit = -1;

        return buildSplit(orientation, range, baseHeight, baseWidth, direction, fit);
    }

    private ArrayList<Area> splitUpRight(int range) throws Exception {
        Orientation orientation = new Orientation("", KOrientation.DIAGONAL_NORTH_EAST);

        int baseHeight = 0;
        int baseWidth = width;
        int direction = 1;
        int fit = 0;

        return buildSplit(orientation, range, baseHeight, baseWidth, direction, fit);
    }

    private ArrayList<Area> splitDownRight(int range) throws Exception {
        Orientation orientation = new Orientation("", KOrientation.DIAGONAL_NORTH_WEST);

        int baseHeight = height;
        int baseWidth = width;
        int direction = -1;
        int fit = -1;

        return buildSplit(orientation, range, baseHeight, baseWidth, direction, fit);
    }

    private ArrayList<Area> splitDownLeft(int range) throws Exception {
        Orientation orientation = new Orientation("", KOrientation.DIAGONAL_NORTH_EAST);

        int baseHeight = height;
        int baseWidth = 0;
        int direction = 1;
        int fit = 0;

        return buildSplit(orientation, range, baseHeight, baseWidth, direction, fit);
    }

    private ArrayList<Area> buildSplit(Orientation o, int range, int height, int width, int direction, int fit) throws Exception {
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

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(height, width, areas);
    }

}