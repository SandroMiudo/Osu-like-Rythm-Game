package pregame.listeners;

import pregame.MyMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionGameSettings implements ActionListener {
    private MyMenu menu;
    private JButton button;

    public ActionGameSettings(MyMenu menu,JButton button){
        this.menu = menu;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            JPanel settingsPanel = createSettingsPanel();
        }
    }

    private JPanel createSettingsPanel(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(new LineBorder(Color.GREEN));
        panel.setBounds(0,0,400,500);
        return panel;
    }
}
