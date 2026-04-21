package startGUI;

import setUpGUI.SetUpController;
import soundMaker.ButtonSound;
import soundMaker.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Controls the main GUI flow of Battle Nations.
 *
 * <p>
 * This class serves as the starting point of the game. Creating a full screen JFrame and handles switching between
 * different display pages, such as start menu, settings page, map choice page, player page, and set up options page.
 * </p>
 *
 * <p>
 * It also handles the game setup data that is modified by the different pages and also all sound features.
 * </p>
 */
public class StartController {

    /** The main JFrame used to hold the different pages. */
    private final JFrame display;

    /** The Start Menu page. */
    private final StartMenuPage start;

    /** The Settings page. */
    private final SettingsPage settings;

    /** The Map Choice page. */
    private final MapChoicePage mapChoice;

    /** The Setup Options page. */
    private final SetUpOptionsPage placementChoice;

    /** The Player Page. */
    private final PlayerPage playerPage;

    /** Creates a new instance of the Setup Data class for storing information. */
    private SetUpData gameSetUpData = new SetUpData();

    /** Handles button click sound effects for the GUI. */
    private final ButtonSound buttonSound;

    /** Handles background music for the GUI. */
    private final Music music;

    /**
     * Constructs a start controller. Initializes and formats the main JFrame display, initializes all pages, and then
     * displays the start menu page.
     */
    public StartController() {
        // Initializes display and formats it.
        display = new JFrame();
        display.setExtendedState(JFrame.MAXIMIZED_BOTH);
        display.setUndecorated(true);
        display.setLayout(null);

        // Creates an image to use for the toolbar.
        ImageIcon icon = new ImageIcon("src/gameImages/GameIcon.png");
        display.setIconImage(icon.getImage());

        // Creates the sound objects for the games.
        buttonSound = new ButtonSound("src/soundFiles/ButtonSound.wav");
        music = new Music("src/soundFiles/StartGUIMusic.wav");

        // Initialize pages
        start = new StartMenuPage();
        settings = new SettingsPage();
        mapChoice = new MapChoicePage();
        playerPage = new PlayerPage();
        placementChoice = new SetUpOptionsPage();

        // Show the display
        display.setVisible(true);

        music.startMusic();

        // Preps the sound file so when the first button is pressed there is no delay.
        buttonSound.playSound(19580);

        // Gets the root of the display so that input and action maps can be retrieved and modified.
        JRootPane root = display.getRootPane();

        // Adds a new input map to the root so that if the s key is pressed the root tries to trigger the associated action map.
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "openSettings");
        // Adds a new action, to the action map.
        root.getActionMap().put("openSettings", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Finds what is the current keyboard focus to make sure that the action does not trigger while trying
                // to enter an s in a text field.
                Component focus = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                if (focus instanceof JTextField) {
                    return;
                }

                // Creates a checkbox for both sound options.
                JCheckBox music = new JCheckBox("Music", getMusic().isMusicEnabled());
                JCheckBox buttonSound = new JCheckBox("Click Sound", isSoundEnabled());

                // Creates an Object list that holds the message text and the two checkboxes.
                Object[] message = {"Sound Settings", music, buttonSound};

                // Creates a popup window with the message.
                JOptionPane.showMessageDialog(display, message, "Settings", JOptionPane.INFORMATION_MESSAGE);

                // Modifies if button sound or music are enabled based on if their respective checkboxes are selected.
                if (music.isSelected()) {
                    getMusic().enableMusic();
                } else {
                    getMusic().disableMusic();
                }
                setSound(buttonSound.isSelected());
                makeSound();
            }
        });

        // Show the start menu by default
        displayStartMenuPage();
    }

    /**
     * Displays the start menu page on the main JFrame display.
     */
    public void displayStartMenuPage() {
        start.addStartMenuPage(this);
    }

    /**
     * Displays the settings page on the main JFrame display.
     */
    public void displaySettingsPage() {
        settings.addSettingsPage(this);
    }

    /**
     * Displays the game setup page on the main JFrame display.
     */
    public void displayPlayerPage() {
        playerPage.addPlayerPage(this);
    }

    /**
     * Displays the map choice page on the main JFrame display.
     */
    public void displayMapChoicePage() {
        mapChoice.addMapChoicePage(this);
    }

    /**
     * Displays set up options page.
     */
    public void displaySetUpOptionsPage() {
        placementChoice.addSetUpOptionsPage(this);
    }

    /**
     * Returns the main JFrame used for the GUI
     *
     * @return The main JFrame used for displaying.
     */
    public JFrame getDisplay() {
        return display;
    }

    /**
     * Returns the setup data object used for storing users choices.
     *
     * @return The SetUpData that stores the users choices.
     */
    public SetUpData getGameSetUpData() {
        return gameSetUpData;
    }

    /**
     * Creates a new SetUpController object passing it the JFrame display, and game set up data.
     */
    public void changeToSetUpController() {
        new SetUpController(this.display, this.gameSetUpData, this);
    }

    /**
     * Players the button click sound effect.
     */
    public void makeSound() {
        // First part of sound file is silent so starts it 2000 frames in.
        buttonSound.playSound(2000);
    }

    /**
     * Checks whether button sound effects are working properly
     *
     * @return true if sound effects are working, false if not.
     */
    public boolean isSoundWorking() {
        return buttonSound.isSoundWorking();
    }

    /**
     * Sets whether the button sound effects are enabled.
     *
     * @param enabled true to enable sound effects, false to disable.
     */
    public void setSound(boolean enabled) {
        buttonSound.setEnabled(enabled);
    }

    /**
     * Checks whether button sound effects are enabled.
     *
     * @return true if sound is enabled, false if not.
     */
    public boolean isSoundEnabled() {
        return buttonSound.isSoundEnabled();
    }

    /**
     * Returns the Music object used for playing background music.
     *
     * @return the Music object
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Resets all the pages to their default state.
     */
    public void reset() {
        gameSetUpData = new SetUpData();
        mapChoice.reset();
        playerPage.reset();
        placementChoice.reset();
        displayStartMenuPage();
    }

    /**
     * Starting point of the code.
     *
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {
        new StartController();
    }
}
