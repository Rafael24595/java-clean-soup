package core.java.entities;

public class Area {

    private Position position;
    private Panel panel;

    public Area(Panel panel, Position position) {
        this.panel = panel;
        this.position = position;
    }

    public int getXPositive() {
        return this.panel.getWidth() - position.getX();
    }

    public int getYPositive() {
        return this.panel.getHeight() - position.getY();
    }

    public int getXNegative() {
        return this.panel.getWidth() - getXPositive();
    }

    public int getYNegative() {
        return this.panel.getHeight() - getYPositive();
    }

}