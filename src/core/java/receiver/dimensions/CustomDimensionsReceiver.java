package core.java.receiver.dimensions;

import core.java.entities.Area;
import core.java.entities.Dimensions;
import core.java.entities.Position;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;

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

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(height, width, areas);
    }

}