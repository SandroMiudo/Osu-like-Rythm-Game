package events;
import starting.game.components.Circle;
import music.Audio;
import scores.Score;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class MouseEvents implements MouseListener {

    enum Timing{
        PERFECT(0,2.0),GREAT(1,1.5),GOOD(2,1.0),OK(3,0.5),
        MISS(4,0.0);
        final int k;
        final double multiplier;

        Timing(int k, double multiplier){
            this.k = k;
            this.multiplier = multiplier;
        }
    }

    //private boolean isClicked = false;
    private Timing timing;
    private int missCounter = 0;
    private int hitCounter = 0;
    private Circle circle;
    private final Score score;

    public MouseEvents(Score score){
        this.score = score;
    }

    /*public synchronized void isClicked() {
        this.isClicked = false;
    }

     */

    public synchronized Score getScore() {
        return score;
    }

    public synchronized int getHitCounter() {
        return hitCounter;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public boolean checkGameOver(){
        if(missCounter >= 5){
            Audio audio = new Audio();
            try {
                audio.createClip("gamesounds/gameover.wav");
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
            audio.startAudio(audio.getClip());
            return true;
        }
        return false;
    }

    private boolean checkPosition(MouseEvent event){
        Point point = event.getPoint();
        Ellipse2D ellipse2D = new Ellipse2D.Double(circle.getPosX(),circle.getPosY(),200,200);
        return ellipse2D.contains(point.getX(), point.getY());
    }

    public void setCircle(Circle circle){
        this.circle = circle;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(this.timing == Timing.MISS || !checkPosition(e)) {
            missCounter++;
            return;
        }
        hitCounter++;
        score.updateScore(hitCounter,this.timing.multiplier);
        //isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {}

    public void setApproachCircle(int total_circles_per_approach, int current_approach_amount) {
        int k = total_circles_per_approach - (total_circles_per_approach - current_approach_amount);
        switch (k) {
            case 0 -> this.timing = Timing.PERFECT;
            case 1 -> this.timing = Timing.GREAT;
            case 2 -> this.timing = Timing.GOOD;
            case 3 -> this.timing = Timing.OK;
            default -> this.timing = Timing.MISS;
        }
    }
}


