package pregame.components;

import javax.swing.*;

public class AudioButton extends JButton{
    private int posX;
    private int posY;

    public AudioButton(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void setButton(){
        this.setBounds(posX,posY,128,128);
        this.setBorderPainted(false);
        this.setOpaque(false);
    }
}
