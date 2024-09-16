package gamemenu.components;

import gamemenu.GameMenu;
import gamemenu.components.SongButton;
import songdata.Song;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SongPanel extends JPanel{
    private int posX = 1500;
    private int posY = 50;
    private List<Song> songs;
    private List<SongButton> songButtons = new ArrayList<>();
    private GameMenu menu;

    public SongPanel(List<Song> songs,GameMenu menu){
        this.songs = songs;
        this.menu = menu;
        setPanel();
    }

    public List<SongButton> getSongButtons() {
        return songButtons;
    }

    public int getPosY() {
        return posY;
    }

    private void setPanel(){
        this.setBounds(posX,posY,420,900);
        this.setLayout(null);
        addSongs();
        this.setOpaque(false);
    }

    private void addSongs(){
        for(Song song: songs){
            SongButton button = new SongButton(song,posY,menu.getFrame());
            songButtons.add(button);
            this.add(button);
            updatePosition();
        }
    }

    public void setPosY() {
        this.posY = 50;
    }

    public void updatePosYSubButtons(){
        this.posY += 55;
    }

    public void posYGap(){
        this.posY += 75;
    }

    private void updatePosition(){
        posY += 130;
    }

}
