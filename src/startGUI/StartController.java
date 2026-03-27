package startGUI;

import setUpGUI.SetUpController;

import javax.swing.*;

/**
 * Controls the main GUI flow of Battle Nations.
 *
 * <p>This class serves as the starting point of the game. Creating a full screen JFrame and handles switching between
 * different display pages, such as start menu, settings page, map choice page, player page, and set up options page.</p>
 *
 */
public class StartController {

    /**
     * The main JFrame used to hold the different pages.
     */
    private final JFrame display;

    /**
     * The Start Menu page.
     */
    private final StartMenuPage start;

    /**
     * The Settings page.
     */
    private final SettingsPage settings;

    /**
     * The Map Choice page.
     */
    private final MapChoicePage mapChoice;

    /**
     * The Setup Options page.
     */
    private final SetUpOptionsPage placementChoice;

    /**
     * The Player Page.
     */
    private final PlayerPage playerPage;

    /**
     * Creates a new instance of the Setup Data class for storing information.
     */
    private final SetUpData gameSetUpData = new SetUpData();

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

        // Initialize pages
        start = new StartMenuPage();
        settings = new SettingsPage();
        mapChoice = new MapChoicePage();
        playerPage = new PlayerPage();
        placementChoice = new SetUpOptionsPage();

        // Show the display
        display.setVisible(true);
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
    public void displayGameSetupPage() {
        playerPage.addGameSetupPage(this);
    }

    /**
     * Displays the map choice page on the main JFrame display.
     */
    public void displayMapChoicePage() {mapChoice.addMapChoicePage(this);}

    /**
     * Displays set up options page
     */
    public void displaySetUpOptionsPage() {
        placementChoice.addSetUpOptionsPage(this);
    }

    /**
     *  Returns the main JFrame used for the GUI
     *
     * @return The main JFrame used for displaying.
     */
    public JFrame getDisplay() {
        return display;
    }

    /**
     *  Returns the setup data object used for storing users choices.
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
        new SetUpController(this.display, this.gameSetUpData);
    }

    /**
     * Starting point the code
     *
     * @param args does something
     */
    public static void main(String[] args) {
        new StartController();
    }
}
