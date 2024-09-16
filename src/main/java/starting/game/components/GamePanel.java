package starting.game.components;

import WinFrame.FrameW;
import WinFrame.ScoreLabel;
import WinFrame.ScorePanel;
import events.KeyEvents;
import events.MouseEvents;
import gamemenu.GameMenu;
import scores.Score;
import songdata.Song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class GamePanel extends JPanel{
    private final String levelInput;
    private final static int total_circles_per_approach = 10;
    private final int alpha_inc;
    private MouseEvents mouseEvents;
    private Timer timer;
    private Timer approachTimer;
    private Circle[] circles;
    private int circleCounter = 0;
    private final Song song;
    private final GameMenu menu;
    private final Map<LocalDateTime, Circle> circleMap = new HashMap<>();
    private List<LocalDateTime> keyList;
    private static final int FACTOR_X = 1920;
    private static final int FACTOR_Y = 1080;
    private static final int [] FACTOR_APPROACH_CIRCLES = {2,3,4,10};  // factor for quickness of approach circles
    private int current_delay;
    private int draw_approach_circle_status = 0;
    private int current_approach_amount;
    private int current_alpha_value;


    public GameMenu getMenu() {
        return menu;
    }

    public Song getSong() {
        return song;
    }

    public MouseEvents getMouseEvents() {
        return mouseEvents;
    }

    public int getCurrent_delay(){
        return current_delay;
    }

    public void setApproachTimer(Timer approachTimer){
        this.approachTimer = approachTimer;
    }

    public int getTotal_circles_per_approach() {
        return total_circles_per_approach;
    }

    public GamePanel(Song song, GameMenu menu, String levelInput){
        this.song = song;
        this.menu = menu;
        this.levelInput = levelInput;
        int factor_approach = approachCirclesFactor(levelInput);// try to manage to increase transition speed without affecting other components
        this.alpha_inc = 255 / total_circles_per_approach;
        this.current_approach_amount = total_circles_per_approach;
        this.current_alpha_value = 0;
        setPanel();
        setScore();
    }

    private Integer approachCirclesFactor(String levelInput){
        return FACTOR_APPROACH_CIRCLES[Integer.parseInt(levelInput.substring(levelInput.length()-1))-1];
    }

    private void revert_circle_approach(){
        current_alpha_value = 0;
        current_approach_amount= total_circles_per_approach;
        this.approachTimer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circleCounter < circles.length) {
            //mouseEvents.isClicked();
            g.setColor(WHITE);
            g.fillOval(circles[circleCounter].getPosX(), circles[circleCounter].getPosY(), 200, 200);
            g.setColor(circles[circleCounter].getColor());
            g.fillOval(circles[circleCounter].getPosX()+10, circles[circleCounter].getPosY()+10, 180,180);
            g.setColor(WHITE);
            g.setFont(new Font("CircleFontNumber",Font.PLAIN,40));
            g.drawString(""+circles[circleCounter].getNumberX(),(circles[circleCounter].getPosX()+10) + 90 - 15,(circles[circleCounter].getPosY()+10) + 100);
            //draw the current Hitpoints of the circle

            g.setFont(new Font("ScoreFont",Font.BOLD,75));
            g.drawString("x"+mouseEvents.getHitCounter(),10,900);
            //draw the current score of the player
            g.drawString(""+(int)mouseEvents.getScore().currentScore(),1600,80);
            // get the frame of the GamePanel and dispose this frame and set the frame of the GameMenu to true
            // *necessary : restart the game of the song -> inject the GameMenu : not only the
            // frame in the GameMenu

            if(draw_approach_circle_status == 1){
                Color c = new Color(WHITE.getRed(),WHITE.getGreen(),WHITE.getBlue(),this.current_alpha_value);
                g.setColor(c);
                g.drawOval(circles[circleCounter].getPosX()-(current_approach_amount * total_circles_per_approach),
                        circles[circleCounter].getPosY()-(current_approach_amount * total_circles_per_approach),
                        200 + (2 * current_approach_amount * total_circles_per_approach),
                        200 + (2 * current_approach_amount * total_circles_per_approach));
                mouseEvents.setApproachCircle(total_circles_per_approach,current_approach_amount);
                current_approach_amount--;
                current_alpha_value += alpha_inc;
            }
            else {
                int diffTime = timeCheck();
                this.current_delay = diffTime;
                timer.setDelay(diffTime);
                draw_approach_circle_status = 1;
                mouseEvents.setCircle(circles[circleCounter]);
            }

            if(current_approach_amount < 0){
                draw_approach_circle_status = 0;
                revert_circle_approach();
                circleCounter++;
            }
            return;
        }
        g.setColor(BLACK);
        g.fillRect(0,0,1920,1080);
        win();
        timer.stop();
        approachTimer.stop();
        song.stop();
    }

    public boolean game_over(){
        return this.mouseEvents.checkGameOver();
    }

    public void return_to_menu(){
        this.getSong().stop();
        JFrame topLevelAncestor = (JFrame)this.getTopLevelAncestor();
        topLevelAncestor.dispose();
        //implement that song is over the Jbuttons of the menu not visible !
        this.getMenu().getFrame().setVisible(true);
        try {
            this.getMenu().restartSong();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException o) {
            o.printStackTrace();
        }
        this.getMenu().removeSubButtons();
        this.timer.stop();
    }

    public void stop_song(){
        this.song.stop_but_not_close();
    }

    public void continue_song(){
        this.song.continue_audio();
    }

    private void win(){
        JFrame topLevelAncestor = (JFrame)this.getTopLevelAncestor();
        topLevelAncestor.dispose();
        ScoreLabel label = new ScoreLabel(mouseEvents.getScore());
        ScorePanel scorePanel = new ScorePanel(label,menu);
        FrameW frameW = new FrameW(scorePanel);
        frameW.setVisible(true);
    }

    private void setScore(){
        Score score = new Score(this);
        mouseEvents = new MouseEvents(score);
        this.addMouseListener(mouseEvents);
        this.addKeyListener(new KeyEvents(this));
    }

    private int timeCheck(){
        if(circleCounter == circles.length-1){
            return 1000;
        }
        LocalDateTime time = keyList.get(circleCounter);
        LocalDateTime time2 = keyList.get(circleCounter+1);
        if (time2.getMinute() > time.getMinute()) {
            return (((time2.getMinute()*60) + time2.getSecond())-((time.getMinute()*60)+ time.getSecond()))*1000;
        }
        return (time2.getSecond() - time.getSecond())*1000;
    }

    public void addTimer(Timer timer){
        this.timer = timer;
    }

    public void initObjects() throws FileNotFoundException {
        int counterNumber = 1;
        int circleCounter = countCircles();
        circles = new Circle[circleCounter];
        for(int i = 0; i < circleCounter; i++){
            double randomX = Math.random();
            double randomY = Math.random();
            circles[i] = new Circle((int)(randomX * FACTOR_X),(int)(randomY * FACTOR_Y),counterNumber++);
            Color color;
            if(i % 3 == 0){
                color = new Color(255,150,0);
            }
            else if(i % 5 == 0){
                color = new Color(255,165,0);
            }
            else{
                color = new Color(255,135,0);
            }
            circles[i].setColorX(color);
            checkPosition(circles[i]);
            if(counterNumber == 5){
                counterNumber = 1;
            }
        }
    }

    public int countCircles() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(levelInput));
        int lines = 0;
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().isEmpty()) {
                break;
            }
            lines++;
        }
        return lines;
    }

    public void initMap() throws Exception {
        Path path = Path.of(levelInput);
        File file = path.toFile();
        Scanner scanner = new Scanner(file);
        int counter = 0;
        while (scanner.hasNextLine()){
            String nextLine = scanner.nextLine();
            if(nextLine.isEmpty()){
                break;
            }
            inputData(nextLine,circles[counter++]);
        }
        Set<LocalDateTime> keySet = circleMap.keySet();
        keysToList(keySet);
    }

    private void checkPosition(Circle circle){
        //100 height , 100 width -> Position has to be 1080-100 / 1920-100
        //if Frame is not full size -> frame.height == 920
        if(circle.getPosX() > 1720){
            circle.setPosX(1720);
        }
        if(circle.getPosY() > 720){
            circle.setPosY(720);
        }
    }

    public void playSong(){   //Songs are saved in Directory under main/resources/songs
        song.start();
    }

    public void stop_timers() {
        if(approachTimer.isRunning()) approachTimer.stop();
        if(timer.isRunning()) timer.stop();
    }

    public void restart_timer() {
        timer.restart();
    }

    private void inputData(String data,Circle circle){
        String[] timeString = data.split(":");
        if(timeString.length == 2) {
            circleMap.put(LocalDateTime.of(0, 1, 1, 0,
                    Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1])), circle);
            return;
        }
        circleMap.put(LocalDateTime.of(0, 1, 1, 0,
                Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]),
                Integer.parseInt(timeString[2])),circle);
    }

    private void keysToList(Set<LocalDateTime> keySet){
        keyList = keySet.stream().sorted(new Comparator<LocalDateTime>(){
            @Override
            public int compare(LocalDateTime time, LocalDateTime time2){
                if(time.getMinute() > time2.getMinute()){
                    return 1;
                }
                if(time.getMinute() == time2.getMinute()){
                    if(time.getSecond() > time2.getSecond()){
                        return 1;
                    }
                    return -1;
                }
                return -1;
            }
        }).collect(Collectors.toList());
    }

    private void setPanel(){
        this.setLayout(null);
        this.setBackground(BLACK);
        this.setBounds(0,0,1920,1080);
    }
}
