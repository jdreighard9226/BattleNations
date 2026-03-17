package gameGUI;

import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameStartup extends JPanel {
    private ArrayList<Player> players;
    private boolean isReady;
    private JFrame parent;

    public GameStartup() {
        JPanel gameStartup = new JPanel();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        gameStartup.setLayout(null);
        gameStartup.setBounds(0, 0, screen.width, screen.height);
    }

    public static void Main(String[] args) {
        GameStartup gameStartup = new GameStartup();

    }

}
