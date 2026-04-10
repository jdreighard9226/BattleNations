package gameGUI;

import player.Player;
import startGUI.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class WinPage {
    private final ImagePanel winPanel;

    public WinPage(GameController  gameController, Player winner, String mode) {
        winPanel = new ImagePanel("src/gameImages/VictoryScreen.png");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        winPanel.setLayout(null);
        winPanel.setBounds(0, 0, screen.width, screen.height);

        int centerX = screen.width / 2;
        int centerY = screen.height / 2;

        JLabel title = new JLabel("Victory!");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(centerX - 200, centerY - 50, 400, 80);
        title.setForeground(Color.WHITE);
        winPanel.add(title);

        JLabel winnerLabel = new JLabel(winner.getName() + " wins by " + mode + "!");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(centerX - 300, centerY + 50, 600, 50);
        winnerLabel.setForeground(Color.WHITE);
        winPanel.add(winnerLabel);

        JButton mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(centerX - 220, centerY + 150, 200, 60);
        mainMenu.addActionListener(e -> gameController.returnToMainMenu());
        winPanel.add(mainMenu);

        JButton exit = new JButton("Exit");
        exit.setBounds(centerX + 20, centerY + 150, 200, 60);
        exit.addActionListener(e -> System.exit(0));
        winPanel.add(exit);
    }

    public JPanel getPanel() {
        return winPanel;
    }
}