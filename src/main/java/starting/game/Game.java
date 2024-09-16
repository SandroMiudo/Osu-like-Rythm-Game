package starting.game;


import pregame.FirstPanel;
import starting.game.components.ApproachCircle;
import starting.game.components.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Game extends JFrame implements ActionListener{

    private final GamePanel panel;
    private Timer t;

    public Game(GamePanel panel) {
        this.panel = panel;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.add(panel);
        this.setVisible(true);
        panel.requestFocusInWindow();
    }

    public void startGame(){
        t = new Timer(0, this);
        t.setInitialDelay(500);
        setTimerInPanel(t);
        t.setRepeats(true);
        t.start();
    }

    private void setTimerInPanel(Timer timer){
        panel.addTimer(timer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        int time_delay = this.panel.getCurrent_delay() / this.panel.getTotal_circles_per_approach();

        Timer timer = new Timer(time_delay,new ApproachCircle(this));
        this.panel.setApproachTimer(timer);
        timer.start();

        if(this.panel.game_over()){
            this.panel.return_to_menu();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new FirstPanel();
    }
}
