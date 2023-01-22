package core.java.entities;

public class Area {

    private Position positionB;
    private Position positionA;

    public Area(Panel panel, Position position) {
        this(new Position(panel.getWidth(), panel.getHeight()), position);
    }

    public Area(Position positionA, Position positionB) {
        this.positionA = positionA;
        this.positionB = positionB;
    }

    public int getXPositionA(){
        return this.positionA.getX();
    }

    public int getXPositionB(){
        return this.positionB.getX();
    }

    public int getYPositionA(){
        return this.positionA.getY();
    }

    public int getYPositionB(){
        return this.positionB.getY();
    }

    public int getXPositive() {
        return this.positionA.getX() - positionB.getX();
    }

    public int getYPositive() {
        return this.positionA.getY() - positionB.getY();
    }

    public int getXNegative() {
        return this.positionA.getX() - getXPositive();
    }

    public int getYNegative() {
        return this.positionA.getY() - getYPositive();
    }

}