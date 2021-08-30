package models;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            InputStream bufferedIn = new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
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
