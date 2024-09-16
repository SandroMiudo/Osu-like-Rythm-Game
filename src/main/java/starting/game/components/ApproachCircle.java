package starting.game.components;

import starting.game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public record ApproachCircle(Game game) implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        game.repaint();
    }
}
