package startGUI;

import javax.swing.*;
import java.awt.*;

public class SetUpOptionsPage {

    private StartController startController;

    private final ImagePanel setUpOptionsPanel;

    public SetUpOptionsPage() {
        setUpOptionsPanel  = new ImagePanel("src/gameImages/OptionScreen.png");

        // Get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setUpOptionsPanel.setLayout(null);
        setUpOptionsPanel.setBounds(0, 0, screen.width, screen.height);

        ButtonGroup territoryButtons = new ButtonGroup();
        JRadioButton randomTerritories = new JRadioButton("Random Territory Selection");
        randomTerritories.setBounds((int) screen.getWidth()/3-125,(int) screen.getHeight()/5 * 2, 250, 40);
        JRadioButton manuelTerritories = new JRadioButton("Manuel Territory Selection");
        manuelTerritories.setBounds((int) screen.getWidth()/3-125, (int) screen.getHeight()/5 * 3, 250, 40);

        territoryButtons.add(randomTerritories);
        territoryButtons.add(manuelTerritories);
        setUpOptionsPanel.add(randomTerritories);
        setUpOptionsPanel.add(manuelTerritories);



        randomTerritories.addActionListener(e -> startController.getGameSetUpData().setRandomTerritories(true));

        manuelTerritories.addActionListener(e -> startController.getGameSetUpData().setRandomTerritories(false));

        ButtonGroup troopButtons = new ButtonGroup();
        JRadioButton randomTroops = new JRadioButton("Random Troop Placement");
        randomTroops.setBounds((int) screen.getWidth()/3*2 - 125,(int) screen.getHeight()/5 * 2, 250, 40);
        JRadioButton manuelTroops = new JRadioButton("Manuel Troop Placement");
        manuelTroops.setBounds((int) screen.getWidth()/3*2 - 125, (int) screen.getHeight()/5 * 3, 250, 40);

        troopButtons.add(randomTroops);
        troopButtons.add(manuelTroops);
        setUpOptionsPanel.add(randomTroops);
        setUpOptionsPanel.add(manuelTroops);



        randomTroops.addActionListener(e -> startController.getGameSetUpData().setRandomTroopPlacement(true));

        manuelTroops.addActionListener(e -> startController.getGameSetUpData().setRandomTroopPlacement(false));

        JButton continueBt = new JButton("Continue");
        continueBt.setBounds((int) screen.getWidth()/2 - 100, (int)screen.getHeight()/6 * 5, 200, 80);
        continueBt.addActionListener(e -> {
            if (territoryButtons.getSelection() != null && troopButtons.getSelection() != null) {
                startController.getDisplay().remove(setUpOptionsPanel);
                startController.changeToSetUpController();
            }
        });
        setUpOptionsPanel.add(continueBt);
    }

    public void addSetUpOptionsPage(StartController startController) {
        this.startController = startController;
        JFrame parent = startController.getDisplay();
        parent.add(setUpOptionsPanel);
        parent.repaint();
    }

}
