package models;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class Sound implements Runnable{
    public String path;
    Clip clip;
    public Sound(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip!=null){
            this.clip.stop();
        }
    }
}
