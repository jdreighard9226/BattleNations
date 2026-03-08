package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController {
    private final JFrame display;
    private final StartMenuPage start;
    private final SettingsPage settings;

    public StartController() {
        display = new JFrame();
        display.setExtendedState(JFrame.MAXIMIZED_BOTH);
        display.setUndecorated(true);
        start = new StartMenuPage();
        settings = new SettingsPage();
        display.setVisible(true);
        displayStartMenuPage();
    }

    public void displayStartMenuPage() {
        start.addStartMenuPage(this);
    }

    public void displaySettingsPage() {
        settings.addSettingsPage(this);
    }

    public JFrame getDisplay() {
        return display;
    }

    public StartMenuPage getStart() {
        return start;
    }

    public SettingsPage getSettings() {
        return settings;
    }

    public static void main(String[] args) {
        StartController myStartController = new StartController();
    }


}
