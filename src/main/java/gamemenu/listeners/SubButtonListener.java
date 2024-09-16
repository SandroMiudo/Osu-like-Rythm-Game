package gamemenu.listeners;

import starting.game.components.GamePanel;
import gamemenu.GameMenu;
import gamemenu.components.SongButton;
import music.Audio;
import starting.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SubButtonListener implements ActionListener{

    private final GameMenu menu;
    private final JButton button;
    private final Audio audio;
    private final SongButton songButton;

    public SubButtonListener(GameMenu menu, JButton button, Audio audio, SongButton songButton) {
        this.menu = menu;
        this.button = button;
        this.audio = audio;
        this.songButton = songButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            menu.setInMenu(false);
            audio.stopAudio(audio.getClip());
            menu.getFrame().setVisible(false);
            String input ="";
            if(button.getText().equals("Easy")){
                input += "src/main/resources/levelInput/diff1";
            }
            else if(button.getText().equals("Medium")){
                input += "src/main/resources/levelInput/diff2";
            }
            else if(button.getText().equals("Hard")){
                input += "src/main/resources/levelInput/diff3";
            }
            else if(button.getText().equals("Very Hard")){
                input += "src/main/resources/levelInput/diff4";
            }
            GamePanel gamePanel = new GamePanel(songButton.getSong(),menu,input);
            Game game = new Game(gamePanel);
            try {
                gamePanel.initObjects();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                gamePanel.initMap();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            gamePanel.playSong();
            game.startGame();
        }
    }
}
