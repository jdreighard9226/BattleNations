package soundMaker;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ButtonSound {

    private Clip buttonSound;

    private boolean soundWorking = true;

    private boolean soundEnabled = true;

    public ButtonSound(String fileName) {

        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        File soundFile = new File(fileName);

        if (!soundFile.exists()) {
            System.err.println("Sound file not found: " + fileName);
            soundWorking = false;
            return;
        }

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            buttonSound = AudioSystem.getClip();
            buttonSound.open(audio);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio type: " + fileExtension);
            soundWorking = false;
        } catch (IOException e) {
            System.err.println("Error reading sound file: " + fileName);
            soundWorking = false;
        } catch (LineUnavailableException e) {
            System.err.println("Audio System unavailable");
            soundWorking = false;
        }
    }

    public boolean isSoundWorking() {
        return soundWorking;
    }

    public void setEnabled(boolean enabled) {
        soundEnabled = enabled;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void playSound(int frameStart) {
        if (soundWorking && soundEnabled) {
            buttonSound.stop();
            buttonSound.setFramePosition(frameStart);
            buttonSound.start();
        }
    }
}
