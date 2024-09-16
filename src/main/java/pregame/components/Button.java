package pregame.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

public class Button{

    private JFrame frame;

    public Button(JFrame frame){
        this.frame = frame;
    }

    public Set<JButton> init(){
        JButton exitButton = new JButton();
        exitButton.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        exitButton.setText("Close Game");
        //exitButton.setIcon(new ImageIcon("starticon.jpg"));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(true);
        exitButton.setOpaque(false);
        exitButton.setBorder(new LineBorder(Color.WHITE));
        exitButton.setBounds(0,550,300,100);

        JButton startButton = new JButton();
        startButton.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        startButton.setText("Start Game");
        //startButton.setIcon(new ImageIcon("start.png"));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(true);
        startButton.setOpaque(false);
        startButton.setBorder(new LineBorder(Color.WHITE));
        startButton.setBounds(0,300,300,100);

        JButton settingsButton = new JButton();
        settingsButton.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,20));
        settingsButton.setText("Settings");
        //settingsButton.setIcon(new ImageIcon("setting.png"));
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBorder(new LineBorder(Color.WHITE));
        settingsButton.setBorderPainted(true);
        settingsButton.setOpaque(false);
        settingsButton.setBounds(0,425,300,100);

        Set<JButton> buttons = new TreeSet<>(new Comparer());
        buttons.add(settingsButton);
        buttons.add(exitButton);
        buttons.add(startButton);
        return buttons;
    }

}
