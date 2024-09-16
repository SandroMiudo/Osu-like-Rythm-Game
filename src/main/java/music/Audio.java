package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Audio {

    private long currentPosition = 0;
    private Clip clip = null;
    private boolean stopped;

    public Clip createClip(String pathname) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Path path = Path.of(pathname);
        File file = path.toFile();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        this.clip = clip;
        return clip;
    }

    public Clip getClip() {
        return clip;
    }

    public boolean songIsRunning(){
        return clip.isRunning();
    }

    public void startAudio(Clip clip){
        clip.start();
    }

    public void closeAudio(Clip clip){
        clip.close();
    }

    public void stopAudio(Clip clip){
        clip.stop();
        this.currentPosition = clip.getMicrosecondPosition();
        stopped = true;
    }

    public void resumeAudio(Clip clip){
        if(stopped){
            clip.setMicrosecondPosition(currentPosition);
            clip.start();
            stopped = false;
        }
    }

    public void resetAudio(Clip clip){
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }
}
