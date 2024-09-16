package pregame.listeners;

import pregame.MyMenu;
import music.Audio;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionGameEnd implements ActionListener {

    private JFrame frame;
    private JButton button;
    private Audio audio;

    public ActionGameEnd(MyMenu menu, JButton button) {
        this.audio = menu.getAudio();
        this.frame = menu.getFrame();
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            audio.closeAudio(audio.getClip());
            Clip clip = null;
            try{
                clip = audio.createClip("gamesounds/gameover.wav");
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
            audio.startAudio(audio.getClip());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            audio.stopAudio(clip);
            audio.closeAudio(clip);
            frame.dispose();
        }
    }
}
