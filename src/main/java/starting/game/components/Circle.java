package starting.game.components;

import java.awt.*;

public class Circle {
    private int posX;
    private int posY;
    private final double radius = 10;
    private int numberX;
    private Color color = null;

    public Circle(int posX, int posY, int numberX) {
        this.posX = posX;
        this.posY = posY;
        this.numberX = numberX;
    }

    public Color getColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public int getNumberX() {
        return numberX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setColorX(Color color){
        this.color = color;
    }

}
