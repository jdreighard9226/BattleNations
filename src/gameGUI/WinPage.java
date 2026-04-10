package gameGUI;

import player.Player;
import startGUI.ImagePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the Win Page displayed at the end of the game.
 *
 * <p>This class creates a victory screen with a background image,
 * displays the winning player and game mode, and provides options
 * to return to the main menu or exit the application.</p>
 *
 * <p>The layout is centered relative to the screen to ensure a clean
 * and consistent visual appearance regardless of resolution.</p>
 */
public class WinPage {

    /**
     * Background panel displaying the victory image.
     */
    private final ImagePanel winPanel;

    /**
     * Constructs the WinPage and initializes all UI components.
     *
     * <p>This includes setting up the background image, centering text,
     * displaying the winner, and adding navigation buttons.</p>
     *
     * @param gameController controller used to handle navigation back to the main menu
     * @param winner the player who won the game
     * @param mode the game mode used (e.g., "Capital Domination")
     */
    public WinPage(GameController  gameController, Player winner, String mode) {

        // Initialize background panel with victory image
        winPanel = new ImagePanel("src/gameImages/VictoryScreen.png");

        // Get screen dimensions for centering UI elements
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        // Use absolute positioning to freely place components
        winPanel.setLayout(null);
        winPanel.setBounds(0, 0, screen.width, screen.height);

        // Calculate center of the screen
        int centerX = screen.width / 2;
        int centerY = screen.height / 2;

        // Create and position the main title
        JLabel title = new JLabel("Victory!");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Center horizontally, slightly above vertical center
        title.setBounds(centerX - 200, centerY - 50, 400, 80);

        // Set text color to white for visibility over background
        title.setForeground(Color.WHITE);
        winPanel.add(title);

        // Create and position winner message
        JLabel winnerLabel = new JLabel(winner.getName() + " wins by " + mode + "!");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center horizontally, slightly below title
        winnerLabel.setBounds(centerX - 300, centerY + 50, 600, 50);

        // Match text color to title
        winnerLabel.setForeground(Color.WHITE);
        winPanel.add(winnerLabel);

        // Create "Main Menu" button
        JButton mainMenu = new JButton("Main Menu");

        // Position left of center below text
        mainMenu.setBounds(centerX - 220, centerY + 150, 200, 60);

        // Return to main menu when clicked
        mainMenu.addActionListener(e -> gameController.returnToMainMenu());
        winPanel.add(mainMenu);

        // Create "Exit" button
        JButton exit = new JButton("Exit");

        // Position right of center
        exit.setBounds(centerX + 20, centerY + 150, 200, 60);

        // Exit application when clicked
        exit.addActionListener(e -> System.exit(0));
        winPanel.add(exit);
    }

    /**
     * Returns the panel containing the win screen UI.
     *
     * <p>This is used by the controller to display the win page
     * within the main application frame.</p>
     *
     * @return the win panel
     */
    public JPanel getPanel() {
        return winPanel;
    }
}