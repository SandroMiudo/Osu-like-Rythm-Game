package events;

import starting.game.components.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvents implements KeyListener {

    private boolean break_ = false;
    private final GamePanel gamePanel;

    public KeyEvents(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(break_){
                gamePanel.return_to_menu();
            }
            gamePanel.stop_song();
            gamePanel.stop_timers();
            break_ = true;
            System.out.println("Paused game! press space to continue!");
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("Resuming game in 100ms!");
            break_ = false;
            gamePanel.restart_timer();
            gamePanel.continue_song();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
