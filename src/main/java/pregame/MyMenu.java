package pregame;

import pregame.components.AudioButton;
import pregame.components.Button;
import pregame.listeners.AudioContinueListener;
import pregame.listeners.AudioResetListener;
import pregame.listeners.AudioStopListener;
import music.Audio;
import pregame.listeners.ActionGameEnd;
import pregame.listeners.ActionGameSettings;
import pregame.listeners.ActionGameStart;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyMenu extends JPanel{
    private JFrame frame;
    private Button button;
    private BufferedImage image = null;
    private Audio audio;

    public MyMenu(JFrame frame, Button button) {
        this.frame = frame;
        this.button = button;
        audio = new Audio();
        setJFrame();
        setPanel();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Audio getAudio() {
        return audio;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,null);
    }

    private void setPanel(){
        this.setBounds(0,0,1600,900);
        this.setLayout(null);
    }

    private void setJFrame(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1600,900);
        frame.setResizable(false);
        frame.setLayout(null);
    }

    private void loadImage() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/startpictures/litmenuwallpaper.jpg");
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMenu() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        Set<JButton> jButtons = button.init();
        initPanel(jButtons);
        loadImage();
        frame.add(this);
    }

    public void playMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Clip clip = audio.createClip("music/tenderfeeling.wav");
        audio.startAudio(clip);
    }

    private void initPanel(Set<JButton> buttons){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(800,0,700,900);
        panel.setOpaque(false);
        for (JButton button : buttons){
            if(button.getText().contains("Close")){
                button.addActionListener(new ActionGameEnd(this,button));
            }
            if(button.getText().contains("Start")){
                button.addActionListener(new ActionGameStart(this,button));
            }
            if(button.getText().contains("Setting")){
                button.addActionListener(new ActionGameSettings(this,button));
            }
            panel.add(button);
        }
        addAudioButtons(panel);
        frame.add(panel);
    }

    private void addAudioButtons(JPanel panel){
        List<AudioButton> audioButtons = new ArrayList<>();
        int posX = 200;
        for(int i = 0; i < 3; i++) {
            AudioButton audioButton = new AudioButton(posX, 0);
            audioButton.setButton();
            audioButtons.add(audioButton);
            panel.add(audioButton);
            posX += 150;
        }
        audioButtons.get(0).addActionListener(new AudioContinueListener(audioButtons.get(0),audio));
        audioButtons.get(1).addActionListener(new AudioStopListener(audioButtons.get(1),audio));
        audioButtons.get(2).addActionListener(new AudioResetListener(audioButtons.get(2),audio));
        addImageIcons(audioButtons);
    }

    private void addImageIcons(List<AudioButton> audioButtons){
        String path = "src/main/resources/audioicons/";
        audioButtons.get(0).setIcon(new ImageIcon(path+"buttonstart.png"));
        audioButtons.get(1).setIcon(new ImageIcon(path+"buttonstop.png"));
        audioButtons.get(2).setIcon(new ImageIcon(path+"undo.png"));
    }
}
