package startGUI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Serves as the start menu page for the battle nations GUI.
 *
 * <p> This class creates a full screen panel with a background image and three main buttons.
 * A start game button which go displays the map choice page. The Settings button which opens
 * up the settings page, and the quit button which exits the program.</p>
 */
public class StartMenuPage {

    /**
     * The main image panel, to which is displayed an image and the buttons.
     */
    private final ImagePanel startMenuPanel;

    /**
     * The parent JFrame that will hold and display the panel.
     */
    private JFrame parent;

    /**
     * The controller that allows navigation between panels.
     */
    private StartController startController;

    /**
     * Constructs the StartMenuPage and initializes all buttons and the background image.
     */
    public StartMenuPage() {
        // Gets the screen size of the host device.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        //Creates an ImagePanel with a given and sets its layout and size.
        startMenuPanel = new ImagePanel("src/gameImages/BattleNationsHomeScreen.png");
        startMenuPanel.setLayout(null);
        startMenuPanel.setBounds(0, 0, screen.width, screen.height);

        // Creates start game button and allows it to remove this panel and call the display map choice page.
        JButton startGame = new JButton("Start Game");
        startGame.setBounds(screen.width / 2 - 100, screen.height / 3, 200, 50);
        startGame.addActionListener(e -> {
            startController.makeSound();
            parent.remove(startMenuPanel);
            startController.displayMapChoicePage();
        });
        startMenuPanel.add(startGame);

        // Settings button that allows it to remove this panel and call the display settings page.
        JButton settings = new JButton("Settings");
        settings.setBounds(screen.width / 2 - 100, screen.height / 2, 200, 50);
        settings.addActionListener(e -> {
            startController.makeSound();
            parent.remove(startMenuPanel);
            startController.displaySettingsPage();
        });
        startMenuPanel.add(settings);

        // Quit button that shuts down the program.
        JButton quit = new JButton("Quit");
        quit.setBounds(screen.width / 2 - 100, 2 * screen.height / 3, 200, 50);
        quit.addActionListener(e -> {
            startController.makeSound();
            System.exit(0);
        });
        startMenuPanel.add(quit);
    }

    /**
     * Adds the start menu panel to the main JFrame display held in the start controller.
     *
     * @param startController the controller managing GUI navigation.
     */
    public void addStartMenuPage(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(startMenuPanel);
        parent.repaint();
        if (!startController.isSoundWorking()) {
            JOptionPane.showMessageDialog(parent, "Button sounds are not working but game will still run.", "Button Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}