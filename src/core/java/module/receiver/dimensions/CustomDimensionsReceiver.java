package core.java.module.receiver.dimensions;

import core.java.entities.Area;
import core.java.entities.Dimensions;
import core.java.entities.Position;
import core.java.module.receiver.dimensions.interfaces.IDimensionsReceiver;
import core.java.tools.splitter.FigureSplitter;

import static core.java.tools.splitter.FigureSplitter.*;

import java.util.ArrayList;

public class CustomDimensionsReceiver implements IDimensionsReceiver {

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
        FigureSplitter splitter = FigureSplitter.getInstance(range, this.width, this.height);

        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case SPLIT_UP_LEFT:
                    areasAux.addAll(splitter.splitUpLeft());
                    break;
                case SPLIT_UP_RIGHT:
                    areasAux.addAll(splitter.splitUpRight());
                    break;
                case SPLIT_DOWN_LEFT:
                    areasAux.addAll(splitter.splitDownLeft());
                    break;
                case SPLIT_DOWN_RIGHT:
                    areasAux.addAll(splitter.splitDownRight());
                    break;
                case SPLIT_DIAMOND:
                    areasAux.addAll(splitter.splitDiamond());
                    break;
                case SPLIT_DIAMOND_AESTHETIC:
                    areasAux.addAll(splitter.splitDiamondAesthetic());
                    break;
                case SPLIT_HEART:
                    areasAux.addAll(splitter.splitHeart());
                    break;
                case INNER_BOX:
                    areasAux.addAll(splitter.splitBox());
                    break;
                default:
                    break;
            }
        }

        return areasAux.toArray(new Area[0]);
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(height, width, areas);
    }

}