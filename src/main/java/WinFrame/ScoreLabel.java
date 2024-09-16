package WinFrame;

import scores.Result;
import scores.Score;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScoreLabel extends JLabel{

    private Score score;

    public ScoreLabel(Score score){
        this.score = score;
        this.setOpaque(false);
        // Score panel dimensions 1000 * 700
        this.setBounds(0,0,1000,400);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        double v = score.currentScore();
        Result result = score.checkResult();
        double maxPoints = score.getTopScore();
        paintScores(g,v,maxPoints);
        paint(result,g);
    }

    private void paintScores(Graphics g,double ownScore, double topScore){
        g.setColor(Color.WHITE);
        g.setFont(new Font("ScoreFont",Font.BOLD,75));
        g.drawString("Your Score : "+ownScore,100,100);
        g.drawString("Max Score : "+topScore,100,200);
    }

    private void paint(Result result,Graphics graphics){
        int posX = 500;
        int posY = 350;
        graphics.setFont(new Font("ScoreFont",Font.BOLD,75));
        graphics.setColor(Color.WHITE);
        graphics.drawString("RESULT : ",100,300);
        graphics.setFont(new Font("BigSize",Font.BOLD,150));
        if(result.equals(Result.A)){
            graphics.setColor(Color.GREEN);
            graphics.drawString("A",posX,posY);
        }
        if(result.equals(Result.B)){
            graphics.setColor(Color.PINK.darker());
            graphics.drawString("B",posX,posY);
        }
        if(result.equals(Result.C)){
            graphics.setColor(Color.PINK.darker());
            graphics.drawString("C",posX,posY);
        }
        if(result.equals(Result.S)){
            graphics.setColor(Color.PINK.darker());
            graphics.drawString("S",posX,posY);
        }
        if(result.equals(Result.SS)){
            graphics.setColor(Color.PINK.darker());
            graphics.drawString("SS",posX,posY);
        }
    }
}
