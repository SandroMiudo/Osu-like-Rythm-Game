package scores;

import starting.game.components.GamePanel;

import java.io.FileNotFoundException;

public class Score {
    private double currentScore = 1000;
    private final double multiplikatorU100 = 10.25;
    private final double multiplikatorU200 = 15.75;
    private final double multiplikatorU300 = 20.25;
    private final double multiplikatorU400 = 25.75;
    private final double multiplikatorU500 = 35.5;
    private final GamePanel gamePanel;
    private double topScore;

    public Score(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void updateScore(int c, double timing_multiplier) {
        if (c <= 100) {
            currentScore += multiplikatorU100 * c * timing_multiplier;
        } else if (c <= 200) {
            currentScore += multiplikatorU200 * c * timing_multiplier;
        } else if (c <= 300) {
            currentScore += multiplikatorU300 * c * timing_multiplier;
        } else if (c <= 400) {
            currentScore += multiplikatorU400 * c * timing_multiplier;
        } else if (c <= 500) {
            currentScore += multiplikatorU500 * c * timing_multiplier;
        }
    }

    public double currentScore() {
        return currentScore;
    }

    private double calcScore() {
        int circleCounter = 0;
        try {
            circleCounter = gamePanel.countCircles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double finalScore = 1000;
        for (int i = 1; i <= circleCounter; i++) {
            if (i <= 100) {
                finalScore += multiplikatorU100 * i;
            } else if (i <= 200) {
                finalScore += multiplikatorU200 * i;
            } else if (i <= 300) {
                finalScore += multiplikatorU300 * i;
            } else if (i <= 400) {
                finalScore += multiplikatorU400 * i;
            }
        }
        return finalScore;
    }

    public Result checkResult() {
        double topScore = calcScore();
        setTopScore(topScore);
        return compareScores(topScore);
    }

    private void setTopScore(double topScore) {
        this.topScore = topScore;
    }

    public double getTopScore() {
        return topScore;
    }

    private Result compareScores(double topScore) {
        Result result;
        if (topScore == currentScore) {
            result = Result.SS;
        } else if (topScore - (topScore * 0.05) <= currentScore) {
            result = Result.S;
        } else if (topScore - (topScore * 0.2) <= currentScore) {
            result = Result.A;
        } else if (topScore - (topScore * 0.3) <= currentScore) {
            result = Result.B;
        } else {
            result = Result.C;
        }
        return result;
    }

}
