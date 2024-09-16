package pregame;

import pregame.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;


class MouseEvent implements MouseListener{
    private JFrame jFrame;
    private JLabel label;

    public MouseEvent(JFrame jFrame,JLabel label) {
        this.jFrame = jFrame;
        this.label = label;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        jFrame.dispose();
        JFrame frame = new JFrame();
        MyMenu menu = new MyMenu(frame,new Button(frame));
        try {
            menu.startMenu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        frame.setVisible(true);
        try {
            menu.playMusic();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {}
}

public class FirstPanel extends JPanel{

    private JFrame frame = new JFrame();

    public FirstPanel() {
        setFrame();
        setPanel();
        addLabel();
        frame.add(this);
        frame.setVisible(true);
    }

    private void setPanel(){
        this.setLayout(null);
        this.setBounds(150,150,500,500);
    }

    private void setFrame(){
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocation(500,100);
    }

    private void addLabel(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("src/main/resources/startpictures/starticon.jpg"));
        label.setBounds(0,0,500,500);
        label.addMouseListener(new MouseEvent(frame,label));
        label.setOpaque(true);
        this.add(label);
    }
}
