package pregame.components;

import javax.swing.*;
import java.util.Comparator;

public class Comparer implements Comparator<JButton> {

    @Override
    public int compare(JButton button1, JButton button2) {
        if(button1.getText().compareTo(button2.getText()) < 0){
            return -1;
        }
        if(button1.getText().compareTo(button2.getText()) > 0){
            return 1;
        }
        return 0;
    }
}
