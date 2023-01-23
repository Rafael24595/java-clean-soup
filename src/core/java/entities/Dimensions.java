package core.java.entities;

public class Dimensions {

    private int height;
    private int width;

    private Area[] freeAreas;

    public Dimensions(int height, int width){
        this(height, width, new Area[0]);
    }

    public Dimensions(int height, int width, Area[] freeAreas){
        this.height = height;
        this.width = width;
        this.freeAreas = freeAreas;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isInFreeArea(int x, int y) {
        for (Area area: freeAreas) {
            int xA = area.getXPositionA();
            int yA = area.getYPositionA();
            int xB = area.getXPositionB();
            int yB = area.getYPositionB();

            boolean swX = (xA <= x && x <= xB) || (xA >= x && x >= xB);
            boolean swY = (yA <= y && y <= yB) || (yA >= y && y >= yB);

            if(swX && swY)
                return true;
        }

        return false;
    }

}