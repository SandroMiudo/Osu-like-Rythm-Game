package gamemenu.listeners;

import gamemenu.GameMenu;
import music.Audio;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class AudioListener implements Runnable{

    private final GameMenu gameMenu;

    public AudioListener(GameMenu gameMenu){
        this.gameMenu = gameMenu;
    }
    @Override
    public void run() {
        Audio audio = gameMenu.getAudio();
        while(audio.songIsRunning()){
        }
        if(gameMenu.isInMenu()) {
            try {
                gameMenu.restartSong();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
