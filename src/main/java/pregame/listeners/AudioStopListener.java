package pregame.listeners;

import pregame.components.AudioButton;
import music.Audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioStopListener implements ActionListener {

    private AudioButton button;
    private Audio audio;

    public AudioStopListener(AudioButton button, Audio audio){
        this.button = button;
        this.audio = audio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            audio.stopAudio(audio.getClip());
        }
    }
}
