package core.java.entities.character;

import core.java.entities.Position;

public class WordCharacter {

    private Position position;
    private java.lang.Character character;

    public WordCharacter(Position position, java.lang.Character character){
        this.position = position;
        this.character = character;
    }

    public java.lang.Character getCharacter() {
        return character;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

}