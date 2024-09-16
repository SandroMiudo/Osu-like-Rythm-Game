package starting.game;

import javax.swing.*;
import java.awt.*;

public class LoadingThread implements Runnable {

    private JLabel label;
    private JFrame frame;
    private boolean componentLoaded = false;

    public LoadingThread(JLabel label,JFrame frame) {
        this.label = label;
        this.frame = frame;
    }

    @Override
    public void run() {
        label.setText("Loading");
        while (!componentLoaded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String append = label.getText();
            label.setText(append + ".");
            label.setFont(new Font("font",Font.BOLD,100));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            frame.setVisible(true);
        }
    }

    public void setLoading(){
        componentLoaded = true;
    }
}
