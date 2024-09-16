package gamemenu;

import gamemenu.components.SongButton;
import gamemenu.components.SongPanel;
import gamemenu.listeners.AudioListener;
import gamemenu.listeners.MenuButtonListener;
import music.Audio;
import songdata.Song;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMenu extends JPanel{
    private JFrame frame = new JFrame();
    private Audio audio = new Audio();
    private List<Song> songs = new ArrayList<>();
    private List<SongButton> buttons = new ArrayList<>();
    private BufferedImage image = null;
    private int[] circleAmount;
    private SongPanel songPanel;
    private boolean inMenu = true;

    public GameMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Clip clip = audio.createClip("music/demonslayer.wav");
        audio.startAudio(clip);
        setFrameMenu();
        setPanelForMenu();
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public void restartSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Clip clip = audio.createClip("music/demonslayer.wav");
        audio.startAudio(clip);
        this.inMenu = true;
        Thread thread = new Thread(new AudioListener(this));
        thread.start();
    }

    public void removeSubButtons(){
        Component[] components = songPanel.getComponents();
        for(int i = 6; i < 10; i++){
            songPanel.remove(components[i]);
        }
    }

    public Audio getAudio() {
        return audio;
    }

    public JFrame getFrame() {
        return frame;
    }

    private void setPanelForMenu() {
        this.setBounds(0,0,1920,1080);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public BufferedImage getImage(){
        return image;
    }

    private void setFrameMenu() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(null);
    }

    public void initSongs(String filename) { // lade Songs aus Datei -> intern gespeichert
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(inputStream != null) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                checkFile(data);
            }
        }
    }

    public void setMenuComponents(String filename) {
        SongPanel panel = new SongPanel(songs,this);
        this.songPanel = panel;
        frame.add(panel);
        setPicture(filename);
        showFrame();
        check(panel);
        Thread thread = new Thread(new AudioListener(this));
        thread.start();
    }

    private void check(SongPanel panel) {
        buttons = panel.getSongButtons();
        panel.setPosY();
        for(SongButton songButton : buttons){
            MenuButtonListener listener = new MenuButtonListener(songButton,this,panel);
            listener.setAudioFromMenu(audio);
            songButton.addActionListener(listener);
        }
    }

    private void setPicture(String filename){
        File file = new File(filename);
        try {
            image = ImageIO.read(file);
            if(image.getWidth() != 1920 || image.getHeight() != 1080){
                throw new Exception("Invalid Size");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkFile(String dataLine) {
        String[] dataSplit = dataLine.split(",");
        if (dataSplit.length != 2) {
            try {
                throw new Exception("Invalid Data!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        songs.add(new Song(dataSplit[0], dataSplit[1]));
    }

    private void showFrame(){
        frame.add(this);
        frame.setVisible(true);
    }

    public void setDiffs(){
        List<String> diffInput = new ArrayList<>();
        diffInput.add("src/main/resources/levelInput/diff1");
        diffInput.add("src/main/resources/levelInput/diff2");
        diffInput.add("src/main/resources/levelInput/diff3");
        diffInput.add("src/main/resources/levelInput/diff4");
        try {
            countCircles(diffInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void countCircles(List<String> diffInput) throws FileNotFoundException{
        int counter = 0;
        circleAmount = new int[diffInput.size()];
        for(String s : diffInput) {
            Scanner scanner = new Scanner(new File(s));
            int lines = 0;
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().isEmpty()) {
                    break;
                }
                lines++;
            }
            circleAmount[counter++] = lines;
        }
    }
}
