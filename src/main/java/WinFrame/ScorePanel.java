package WinFrame;

import gamemenu.GameMenu;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ButtonListener implements ActionListener {

    private final JButton button;
    private final GameMenu menu;

    public ButtonListener(JButton button,GameMenu menu){
        this.button = button;
        this.menu = menu;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            FrameW frameW = (FrameW) button.getTopLevelAncestor();
            frameW.dispose();
            menu.getFrame().setVisible(true);
            try {
                menu.restartSong();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
            menu.removeSubButtons();
        }
    }
}

public class ScorePanel extends JPanel{
    private GameMenu menu;

    public ScorePanel(ScoreLabel scoreLabel, GameMenu menu){
        this.menu = menu;
        this.setBounds(460,100,1000,700);
        this.setOpaque(false);
        this.setLayout(null);
        initButton();
        this.add(scoreLabel);
    }

    private void initButton(){
        JButton button = new JButton();
        button.setBorder(new LineBorder(Color.WHITE));
        button.setBounds(400,500,300,200);
        button.setFont(new Font("MediumFont",Font.BOLD,30));
        button.setForeground(Color.WHITE);
        button.setText("Continue");
        button.setBorderPainted(true);
        button.setOpaque(false);
        button.addActionListener(new ButtonListener(button,menu));
        this.add(button);
    }
}
