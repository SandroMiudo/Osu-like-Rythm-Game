package scores;

import starting.game.components.GamePanel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestScore {
    @Test
    @DisplayName("Checks: if all balls are hit the score is set to top score")
    public void test_1() throws FileNotFoundException {
        GamePanel gamePanel = mock(GamePanel.class);
        Score score = new Score(gamePanel);
        when(gamePanel.countCircles()).thenReturn(5);
        score.updateScore(1,0.1);
        score.updateScore(2,0.1);
        score.updateScore(3,0.1);
        score.updateScore(4,0.1);
        score.updateScore(5,0.1);
        double currentScore = score.currentScore();
        Result result = score.checkResult();
        double topScore = score.getTopScore();
        Assertions.assertThat(currentScore).isEqualTo(topScore);
        Assertions.assertThat(result).isEqualTo(Result.SS);
    }
}
