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
    private JFrame parent;
    private StartController startController;

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
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                parent.remove(settingsPage);
                startController.displayStartMenuPage();
            }
        });
        settingsPage.add(back);
    }

    public void addSettingsPage(StartController startController) {
        this.parent = startController.getDisplay();
        this.startController = startController;
        parent.add(settingsPage);
        parent.repaint();
    }


}

