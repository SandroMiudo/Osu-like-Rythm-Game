package gamemenu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import songdata.Song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMenu {
    @DisplayName("Read Data from File Correctly")
    @Test
    public void test_1() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameMenu gameMenu = new GameMenu();
        gameMenu.initSongs("/Users/sandromiudo/Desktop/OwnProjects/src/test/resources/songtext.txt");
        List<Song> songs = gameMenu.getSongs();
        assertThat(songs.size()).isEqualTo(6);

    }

    @DisplayName("Test if the frame is set correct")
    @Test
    public void test_2() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameMenu gameMenu = new GameMenu();
        Dimension size = gameMenu.getSize();
        assertThat(size.height).isEqualTo(1080);
        assertThat(size.width).isEqualTo(1920);
    }

    @DisplayName("Tests if image is loaded correctly into frame")
    @Test
    public void test_3() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameMenu menu = new GameMenu();
        menu.setMenuComponents("src/test/resources/testpictures/testanimewallpaper.png");
        BufferedImage image = menu.getImage();
        Graphics graphics = image.getGraphics();
        assertThat(image).isNotNull();
        assertThat(graphics).isNotNull();
        assertThat(menu.getImage().getHeight()).isEqualTo(1080);
    }

    @DisplayName("Should display all components in the menu")
    @Test
    public void test_4() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameMenu menu = new GameMenu();
        menu.initSongs("src/test/resources/songtext.txt");
        menu.setMenuComponents("src/test/resources/testpictures/testanimewallpaper.png");
    }
}
