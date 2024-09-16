package gamemenu.components;

import songdata.Song;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SongButton extends JButton{

    private Song song;
    private int posY;
    private JFrame frame;
    private boolean isActive = false;

    public SongButton(Song song,int posY,JFrame frame){
        this.song = song;
        this.posY = posY;
        this.frame = frame;
        setButton();
    }

    public Song getSong() {
        return song;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("Big",Font.BOLD,20));
        g.drawString(song.getTitel(),5,15);
        g.setFont(new Font("Big",Font.ITALIC,15));
        g.drawString(song.getSinger(),5,40);
    }

    private void setButton(){
        this.setBounds(5,posY+5,150,100);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setOpaque(false);
        this.setBorderPainted(true);
    }
}
