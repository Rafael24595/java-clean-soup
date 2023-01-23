package core.java.tools.splitter;

import core.java.entities.Area;
import core.java.entities.Position;

import java.util.ArrayList;

public class FigureSplitter extends PanelSplitter {

    public static final String SPLIT_DIAMOND = "SPLIT_DIAMOND";
    public static final String SPLIT_DIAMOND_AESTHETIC = "SPLIT_DIAMOND_AESTHETIC";
    public static final String SPLIT_HEART = "SPLIT_HEART";
    public static final String INNER_BOX = "INNER_BOX";

    private FigureSplitter(int range, int width , int height){
        super(range, width, height);
    }

    public static FigureSplitter getInstance(int range, int width, int height){
        return new FigureSplitter(range, width, height);
    }

    public ArrayList<Area> splitBox() {
        ArrayList<Area> collection = new ArrayList<>();
        int padding = this.range;

        if(padding * 2 < width && padding * 2 < height){
            int paddingBottom = padding + 1;
            Position positionA = new Position(padding, padding);
            Position positionB = new Position(width - paddingBottom, height - paddingBottom);
            Area area = new Area(positionA, positionB);

            collection.add(area);
        }

        return collection;
    }

    public ArrayList<Area> splitHeart() throws Exception {
        ArrayList<Area> collection = new ArrayList<>();

        int rangeDTop = (int) (height * 0.2) * range;
        int rangeDDown = (int) (height * 0.7) * range;

        collection.addAll(splitDiamond(rangeDTop, rangeDDown));

        int fitH = (int) (width * 0.6) * range;
        int rangeHTop = (int) (height * 0.2) * range;

        collection.addAll(splitUpLeft(rangeHTop, fitH));
        collection.addAll(splitUpRight(rangeHTop, -fitH));

        return collection;
    }

    public ArrayList<Area> splitDiamond() throws Exception {
        return splitDiamond(range, range);
    }

    public ArrayList<Area> splitDiamondAesthetic() throws Exception {
        int rangeTop = (int) (height * 0.3) * range;
        int rangeDown = (int) (height * 0.7) * range;

        return splitDiamond(rangeTop, rangeDown);
    }

    ArrayList<Area> splitDiamond(int rangeTop, int rangeDown) throws Exception {
        ArrayList<Area> collection = new ArrayList<>();
        collection.addAll(splitUpLeft(rangeTop));
        collection.addAll(splitUpRight(rangeTop));
        collection.addAll(splitDownLeft(rangeDown));
        collection.addAll(splitDownRight(rangeDown));

        return collection;
    }

}