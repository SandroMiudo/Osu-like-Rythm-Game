package startgame;

import starting.game.components.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class TestGame {
    @Test
    @DisplayName("Wenn das Spiel gestartet wird sollen alle Buttons im Set sein")
    public void test_1(){
        GamePanel gamePanel = mock(GamePanel.class);

    }
}
