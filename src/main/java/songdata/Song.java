package songdata;

import music.Audio;

import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Song {
    private Audio audio;
    private String titel;
    private String singer;
    private int diff;

    public String getTitel() {
        return titel;
    }

    public String getSinger() {
        return singer;
    }

    public Song(String titel, String singer){
        this.titel = titel;
        this.singer = singer;
    }

    public void start(){
        String s = changeName();
        if(checkSongIsFile(s)){
            audio = new Audio();
            try {
                audio.createClip("src/main/resources/songs/"+s);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
            audio.startAudio(audio.getClip());
        }
    }

    public void continue_audio(){
        audio.resumeAudio(audio.getClip());
    }

    public void stop_but_not_close(){
        audio.stopAudio(audio.getClip());
    }

    public void stop(){
        audio.stopAudio(audio.getClip());
        audio.closeAudio(audio.getClip());
    }

    public boolean isFinished(){
        if(audio.songIsRunning()){
            return false;
        }
        return true;
    }

    private boolean checkSongIsFile(String s){
        File file = new File("src/main/resources/songs/"+s);
        if(!file.exists()){
            try {
                throw new Exception("File does not exist!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        if(!file.canRead()){
            try {
                throw new Exception("Can't read File");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    private String changeName(){
        String str = titel.toLowerCase();
        return str.replaceAll(" ", "")+".wav";
    }
}
