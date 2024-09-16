package gamemenu.listeners;

import gamemenu.GameMenu;
import gamemenu.components.SongButton;
import gamemenu.components.SongPanel;
import music.Audio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener{

    private SongButton button;
    private Audio audio;
    private GameMenu menu;
    private SongPanel panel;
    private int actionListenerCounter = 0;
    private boolean buttonPressedAgain = false;

    public MenuButtonListener(SongButton button,GameMenu menu,SongPanel panel){
        this.button = button;
        this.menu = menu;
        this.panel = panel;
    }
    //If Song is selected setVisible of the GameMenu's Frame to false and create a new GamePanel with the selected song
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            if(buttonPressedAgain){
                Component[] components = panel.getComponents();
                for(int i = 6; i < 10; i++){
                   panel.remove(components[i]);
                }
                menu.getFrame().repaint();
                buttonPressedAgain = false;
                return;
            }
            if(!checkOtherSubButtons()){
                return;
            }
            buttonPressedAgain = true;
            createDiffLevels();
            menu.setDiffs();
            actionListenerCounter = 0;
            menu.getFrame().repaint();
        }
    }

    public boolean checkOtherSubButtons(){
        Component[] components = panel.getComponents();
        if(components.length > 6){  // more subcomponents than allowed!
            return false;
        }
        return true;
    }

    private void createDiffLevels(){ // create Buttons with various levels on current Button
        int counter = 0;
        int initialValue = 160;
        int buttonY = button.getY();
        for (int i = 0; i < 4; i++) {
            setButton(initialValue,buttonY);
            counter++;
            if (counter == 2) {
                initialValue = 160;
                buttonY += 55;
                continue;
            }
            initialValue += 135;
        }
    }

    public void setAudioFromMenu(Audio audio){
        this.audio = audio;
    }

    private void setButton(int valX,int valY){
        JButton button = new JButton();
        button.setBounds(valX,valY,125,45);
        button.setBorder(new LineBorder(Color.BLACK));
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        if(actionListenerCounter == 0){
            button.setText("Easy");
        }
        else if(actionListenerCounter == 1){
            button.setText("Medium");
        }
        else if(actionListenerCounter == 2){
            button.setText("Hard");
        }
        else if(actionListenerCounter == 3){
            button.setText("Very Hard");
        }
        panel.add(button);
        button.addActionListener(new SubButtonListener(menu,button,audio,this.button));
        actionListenerCounter++;
    }
}
