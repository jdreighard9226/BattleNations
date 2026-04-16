package soundMaker;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

        private Clip music;

        private boolean soundWorking = true;

        private boolean soundEnabled = true;

        public Music(String fileName) {

            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            File soundFile = new File(fileName);

            if (!soundFile.exists()) {
                System.err.println("Sound file not found: " + fileName);
                soundWorking = false;
                return;
            }

            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
                music = AudioSystem.getClip();
                music.open(audio);
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

        public boolean isMusicWorking() {
            return soundWorking;
        }

        public boolean isMusicEnabled() {
            return soundEnabled;
        }

        public void disableMusic(){
            soundEnabled = false;
            music.stop();
        }

        public void enableMusic() {
            soundEnabled = true;
            startMusic();
        }

        public void startMusic() {
            if (soundWorking && soundEnabled) {
                music.loop(999999999);
            }
        }
    }

