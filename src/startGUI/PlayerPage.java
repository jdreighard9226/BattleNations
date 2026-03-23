package startGUI;

import player.Player;
import setUpGUI.SetUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPage {

    /**
     * Background panel
     */
    private final ImagePanel gameSetupPanel;

    /**
     * Player input field
     */
    private final JTextField playerName;

    /**
     * Add player button
     */
    private final JButton addPlayer;

    /**
     * Start game button
     */
    private final JButton startGame;

    /**
     * List to display players
     */
    private final DefaultListModel<String> playerListModel;
    private final JList<String> playerList;

    /**
     * Stored players
     */
    private final ArrayList<Player> players;

    /**
     * Parent + controller
     */
    private JFrame parent;
    private StartController startController;

    public PlayerPage() {
        players = new ArrayList<>();

        gameSetupPanel = new ImagePanel("src/gameImages/OptionScreen.png");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        gameSetupPanel.setLayout(null);
        gameSetupPanel.setBounds(0, 0, screen.width, screen.height);

        JLabel title = new JLabel("Game Setup");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(screen.width / 2 - 150, 80, 300, 40);
        gameSetupPanel.add(title);

        JLabel nameLabel = new JLabel("Player Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBounds(screen.width / 2 - 100, 160, 200, 30);
        gameSetupPanel.add(nameLabel);

        playerName = new JTextField();
        playerName.setBounds(screen.width / 2 - 100, 200, 200, 30);
        gameSetupPanel.add(playerName);

        addPlayer = new JButton("Add Player");
        addPlayer.setFont(new Font("Arial", Font.BOLD, 18));
        addPlayer.setBounds(screen.width / 2 - 100, 240, 200, 40);
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });
        gameSetupPanel.add(addPlayer);

        playerListModel = new DefaultListModel<>();
        playerList = new JList<>(playerListModel);

        JScrollPane scroll = new JScrollPane(playerList);
        scroll.setBounds(screen.width / 2 - 150, 300, 300, 150);
        gameSetupPanel.add(scroll);

        startGame = new JButton("Continue");
        startGame.setBounds(screen.width / 2 - 100, 480, 200, 50);
        startGame.setEnabled(false);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.getGameSetUpData().setPlayers(players);
                startGame();
            }
        });
        gameSetupPanel.add(startGame);
    }

    public void addGameSetupPage(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(gameSetupPanel);
        parent.repaint();
    }

    private void addPlayer() {
        String name = playerName.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Enter a player name.");
            return;
        }

        if (players.size() >= 6) {
            JOptionPane.showMessageDialog(parent, "Max 6 players.");
            return;
        }

        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(parent, "Name already used.");
                return;
            }
        }

        // color is a placeholder for now, until I can add the Selecting color logic
        players.add(new Player(name, Color.GRAY));
        playerListModel.addElement(name);

        playerName.setText("");

        if (players.size() >= 2) {
            startGame.setEnabled(true);
        }
    }

    private void startGame() {
        parent.remove(gameSetupPanel);
        startController.displayTerritoryTroopPlacementOptionPage();
    }
}