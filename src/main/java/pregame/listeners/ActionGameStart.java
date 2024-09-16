package pregame.listeners;

import pregame.MyMenu;
import gamemenu.GameMenu;
import music.Audio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionGameStart implements ActionListener {

    private JFrame frame;
    private JButton button;
    private Audio audio;

    public ActionGameStart(MyMenu menu,JButton button) {
        this.frame = menu.getFrame();
        this.button = button;
        this.audio = menu.getAudio();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            frame.dispose();
            audio.closeAudio(audio.getClip());
            GameMenu menu = null;
            try {
                menu = new GameMenu();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
            menu.initSongs("src/main/resources/songs/data/songtext.txt");
            menu.setMenuComponents("src/main/resources/pictures/winwallpaper.png");
        }
    }
}
