package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage {
    private final ImagePanel settingsPage;
    private final JCheckBox sound;
    private final JCheckBox music;
    private final JButton back;

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

        back = new JButton("Back To Start");
        back.setBounds(screen.width/2 - 100, 2 * screen.height/3, 200, 50);
        settingsPage.add(back);
    }

    public void addSettingsPage(JFrame parent, ActionListener listener) {
        parent.add(settingsPage);
        back.addActionListener(listener);
        parent.repaint();
    }

    public void removeSettingsPage(JFrame parent) {
        parent.remove(settingsPage);
        parent.repaint();
    }

}

