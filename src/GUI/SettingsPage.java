package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage {
    ImagePanel settingsPage;
    JCheckBox sound;
    JCheckBox music;
    JButton quit;

    public SettingsPage() {
        settingsPage = new ImagePanel("src/Images/SettingsImage.png");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        settingsPage.setLayout(null);
        settingsPage.setBounds(0, 0, screen.width, screen.height);

        sound = new JCheckBox("Sound Affects", true);
        sound.setBounds(screen.width/2 - 100, screen.height/2, 200, 40);
        settingsPage.add(sound);

        music = new JCheckBox("Music", true);
        music.setBounds(screen.width/2 - 100, screen.height/2+80, 200, 40);
        settingsPage.add(music);

        quit = new JButton("Quit");
        quit.setBounds(screen.width/2 - 100, 2 * screen.height/3, 200, 50);
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        settingsPage.add(quit);
    }

    public void addSettingsPage(JFrame parent) {
        parent.add(settingsPage);
        parent.repaint();
    }

    public static void main(String[] args) {
        new Controler();

    }
}

