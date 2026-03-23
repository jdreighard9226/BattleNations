package startGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerritoryTroopPlacementOptionPage {

    private StartController startController;

    private final ImagePanel territoryTroopPlacementOptionPanel;

    public TerritoryTroopPlacementOptionPage() {
        territoryTroopPlacementOptionPanel  = new ImagePanel("src/gameImages/OptionScreen.png");

        // Get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        territoryTroopPlacementOptionPanel.setLayout(null);
        territoryTroopPlacementOptionPanel.setBounds(0, 0, screen.width, screen.height);

        ButtonGroup territoryButtons = new ButtonGroup();
        JRadioButton randomTerritories = new JRadioButton("Random Territory Selection");
        randomTerritories.setBounds((int) screen.getWidth()/3-125,(int) screen.getHeight()/5 * 2, 250, 40);
        JRadioButton manuelTerritories = new JRadioButton("Manuel Territory Selection");
        manuelTerritories.setBounds((int) screen.getWidth()/3-125, (int) screen.getHeight()/5 * 3, 250, 40);

        territoryButtons.add(randomTerritories);
        territoryButtons.add(manuelTerritories);
        territoryTroopPlacementOptionPanel.add(randomTerritories);
        territoryTroopPlacementOptionPanel.add(manuelTerritories);



        randomTerritories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.getGameSetUpData().setRandomTerritories(true);
            }
        });

        manuelTerritories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.getGameSetUpData().setRandomTerritories(false);
            }
        });

        ButtonGroup troopButtons = new ButtonGroup();
        JRadioButton randomTroops = new JRadioButton("Random Troop Selection");
        randomTroops.setBounds((int) screen.getWidth()/3*2 - 125,(int) screen.getHeight()/5 * 2, 250, 40);
        JRadioButton manuelTroops = new JRadioButton("Manuel Troop Selection");
        manuelTroops.setBounds((int) screen.getWidth()/3*2 - 125, (int) screen.getHeight()/5 * 3, 250, 40);

        troopButtons.add(randomTroops);
        troopButtons.add(manuelTroops);
        territoryTroopPlacementOptionPanel.add(randomTroops);
        territoryTroopPlacementOptionPanel.add(manuelTroops);



        randomTroops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.getGameSetUpData().setRandomTroopPlacement(true);
            }
        });

        manuelTroops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.getGameSetUpData().setRandomTroopPlacement(false);
            }
        });

        JButton continueBt = new JButton("Continue");
        continueBt.setBounds((int) screen.getWidth()/2 - 100, (int)screen.getHeight()/6 * 5, 200, 80);
        continueBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (territoryButtons.getSelection() != null && troopButtons.getSelection() != null) {
                    startController.getDisplay().remove(territoryTroopPlacementOptionPanel);
                    startController.changeToSetUpController();
                }
            }
        });
        territoryTroopPlacementOptionPanel.add(continueBt);
    }

    public void addTerritoryTroopPlacementOptionPage(StartController startController) {
        this.startController = startController;
        JFrame parent = startController.getDisplay();
        parent.add(territoryTroopPlacementOptionPanel);
        parent.repaint();
    }

}
