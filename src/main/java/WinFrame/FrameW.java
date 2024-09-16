package WinFrame;

import gamemenu.GameMenu;
import scores.Result;
import scores.Score;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FrameW extends JFrame{

    public FrameW(ScorePanel panel) {
        setFrame();
        this.add(panel);
        this.setVisible(true);
    }

    private void setFrame(){
        this.setSize(1920,920);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK.brighter());
        this.setLayout(null);
    }
}
