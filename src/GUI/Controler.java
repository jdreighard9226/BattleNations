package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler {
    final JFrame display;
    final StartMenu start;
    final SettingsPage settings;

    public Controler() {
        display = new JFrame();
        display.setExtendedState(JFrame.MAXIMIZED_BOTH);
        display.setUndecorated(true);
        start = new StartMenu();
        settings = new SettingsPage();
        displayStartMenu();
    }

    public void displayStartMenu() {
        ActionListener startMenuSettingsListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start.removeFromParent(display);
                displaySettingsPage();
            }
        };
        start.addStartMenu(display, startMenuSettingsListener);
    }

    public void displaySettingsPage() {
        settings.addSettingsPage(display);
    }

    public static void main(String[] args) {
         //Might want to use this later so we have full control over how everything looks
        Controler myControler = new Controler();
    }
}
