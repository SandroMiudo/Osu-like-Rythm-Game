package pregame.listeners;

import pregame.components.AudioButton;
import music.Audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioContinueListener implements ActionListener{

    private final AudioButton button;
    private final Audio audio;

    public AudioContinueListener(AudioButton button, Audio audio){
        this.button = button;
        this.audio = audio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            audio.resumeAudio(audio.getClip());
        }
    }
}
