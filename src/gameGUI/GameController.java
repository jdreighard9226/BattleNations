package gameGUI;

import game.*;
import map.MapPanel;
import map.Territory;
import map.TotalDominationWorld;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameController {
    private final GameLogic gameLogic;
    private final List<Territory> territories;
    private final JFrame display;
    private final JLabel gameStatusLabel;
    private final JLabel instructionText;
    private final JLabel generalInfoLabel;
    private final JLabel errorText;
    private final JLabel successText;

    private final JPanel gameInfoPanel;
    private final JButton continueButton;

    private Territory firstTerritoryClicked;

    private boolean fortifyUsedThisTurn;

    public GameController(TotalDominationWorld world, List<Player> players, JFrame display, MapPanel mapPanel, JPanel gameInfoPannel, JLabel gameStatusLabel, JLabel generalInfoLabel, JLabel instructionText, JLabel errorText, JLabel successText){
        gameLogic = new GameLogic(world, players, new AttackService(), new ReinforcementService(), new FortifyService());
        gameLogic.calculateReinforcement();
        territories = world.getAllTerritories();
        fortifyUsedThisTurn = false;

        this.display = display;
        this.gameInfoPanel = gameInfoPannel;
        this.gameStatusLabel = gameStatusLabel;
        this.generalInfoLabel = generalInfoLabel;
        this.instructionText = instructionText;
        this.errorText = errorText;
        this.successText = successText;

        continueButton = new JButton("Continue");
        continueButton.setBounds(900, 40, 180, 50);
        continueButton.setEnabled(false);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTurn();
            }
        });
        gameInfoPanel.add(continueButton);
        mapPanel.switchToGameController(this);
        updateText();
        display.revalidate();
        display.repaint();
    }

    public void getTerritoryClicked(Point point) {
        errorText.setText("");
        successText.setText("");
        for (Territory territory : territories) {
            if (territory != null && territory.contains(point)) {
                if (gameLogic.getCurrentPhase() == TurnPhase.REINFORCEMENT) {
                    reinforcementPhaseTerritoryClicked(territory);
                } else if (gameLogic.getCurrentPhase() == TurnPhase.ATTACK) {
                    attackPhaseTerritoryClick(territory);
                } else {
                    fortifyPhaseTerritoryClicked(territory);
                }
                updateText();
                return;
            }
        }
        errorText.setText("Error: Click a Playable Territory");
    }

    private void attackPhaseTerritoryClick(Territory territory) {
        if (firstTerritoryClicked == null) {
            if (!gameLogic.isTerritoryPlayerOwned(territory)) {
                errorText.setText("ERROR: Select a territory you own to attack from");
                return;
            }

            if (territory.getTroopAmount() <= 1) {
                errorText.setText("ERROR: Not enough troops to attack from this territory");
                return;
            }

            firstTerritoryClicked = territory;

        } else {

            if (gameLogic.isTerritoryPlayerOwned(territory)) {
                errorText.setText("ERROR: Select a valid territory you do not own to attack");
                return;
            }

            ValidationResult result = gameLogic.attack(firstTerritoryClicked, territory);
            if (!result.isValid()) {
                errorText.setText(result.message());
                return;
            }
            successText.setText(result.message());
            firstTerritoryClicked = null;
        }
    }

    private void reinforcementPhaseTerritoryClicked(Territory territory) {
        if (gameLogic.getCurrentPlayer().getTroopsToPlace() <= 0) {
            errorText.setText("No troops left to place.");
            return;
        }

        if (!gameLogic.isTerritoryPlayerOwned(territory)) {
            errorText.setText("Select an owned territory to reinforce");
            return;
        }

        firstTerritoryClicked = territory;
        Integer troopsToPlace = getTroopAmountToPlaceOnTerritories(gameLogic.getCurrentPlayer().getTroopsToPlace());
        if (troopsToPlace == null) {
            firstTerritoryClicked = null;
            return;
        }

        ValidationResult result = gameLogic.reinforce(gameLogic.getCurrentPlayer(), territory, troopsToPlace);
        if (!result.isValid()) {
            errorText.setText(result.message());
            firstTerritoryClicked = null;
            return;
        }
        successText.setText(result.message());
        firstTerritoryClicked = null;
    }

    private void fortifyPhaseTerritoryClicked(Territory territory) {
        if (fortifyUsedThisTurn) {
            errorText.setText("Fortify already used this turn. Press Continue.");
            return;
        }
        if (firstTerritoryClicked == null) {
            if (!gameLogic.isTerritoryPlayerOwned(territory)) {
                errorText.setText("Select a territory YOU OWN to move troops from");
                return;
            }

            if (territory.getTroopAmount() <= 1) {
                errorText.setText("Not enough troops to move");
                return;
            }

            firstTerritoryClicked = territory;
            return;
        }

        if (territory == firstTerritoryClicked) {
            firstTerritoryClicked = null;
            return;
        }
        Integer troopsToMove = getTroopAmountToMoveFromTerritory(firstTerritoryClicked.getTroopAmount() - 1);

        if (troopsToMove == null) {
            return;
        }

        ValidationResult result = gameLogic.fortify(firstTerritoryClicked, territory, troopsToMove);

        if (!result.isValid()) {
            errorText.setText(result.message());
            return;
        }

        successText.setText(result.message());
        instructionText.setText("Forify Stage done, Press Continue Button");
        firstTerritoryClicked = null;
        fortifyUsedThisTurn = true;
        continueButton.setEnabled(true);

    }

    private Integer getTroopAmountToPlaceOnTerritories(int maxTroops) {
        Integer[] options = new Integer[maxTroops];
        for (int i = 0; i < maxTroops; i++) {
            options[i] = i + 1;
        }

        Object selection = JOptionPane.showInputDialog(
                display,
                "Select number of troops to place:",
                "Troop Placement",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selection == null) {
            return null;
        }

        return (Integer) selection;
    }

    private Integer getTroopAmountToMoveFromTerritory(int maxTroops) {
        Integer[] options = new Integer[maxTroops];
        for (int i = 0; i < maxTroops; i++) {
            options[i] = i + 1;
        }

        Object selection = JOptionPane.showInputDialog(
                display,
                "Select number of troops to move:",
                "Troop Movement",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selection == null) {
            return null;
        }

        return (Integer) selection;
    }

    private void endTurn() {
        firstTerritoryClicked = null;
        fortifyUsedThisTurn = false;
        errorText.setText("");
        successText.setText("");
        gameLogic.changePhase();

        if (gameLogic.getCurrentPhase() == TurnPhase.REINFORCEMENT) {
            gameLogic.calculateReinforcement();
        }

        updateText();
    }

    private void updateText() {
        Player currentPlayer = gameLogic.getCurrentPlayer();

        if (gameLogic.getCurrentPhase() == TurnPhase.REINFORCEMENT) {
            gameStatusLabel.setText("REINFORCEMENT");
            generalInfoLabel.setText(
                    "Player: " + currentPlayer.getName()
                            + " | Troops left: " + currentPlayer.getTroopsToPlace()
            );

            instructionText.setText("Select a territory you own to reinforce.");
            continueButton.setEnabled(currentPlayer.getTroopsToPlace() <= 0);

        } else if (gameLogic.getCurrentPhase() == TurnPhase.ATTACK) {
            gameStatusLabel.setText("ATTACK");
            generalInfoLabel.setText("Current Player: " + currentPlayer.getName());

            if (firstTerritoryClicked == null) {
                instructionText.setText("Select a territory you own to attack from.");
            } else {
                instructionText.setText("Select a territory to attack.");
            }

            continueButton.setEnabled(true);

        } else {
            gameStatusLabel.setText("FORTIFY");
            generalInfoLabel.setText("Current Player: " + currentPlayer.getName());

            if (fortifyUsedThisTurn) {
                instructionText.setText("Fortify complete. Press Continue to end your turn.");
            } else if (firstTerritoryClicked == null) {
                instructionText.setText("Select a territory you own to move troops from.");
            } else {
                instructionText.setText("Select a territory to move troops to.");
            }

            continueButton.setEnabled(true);
        }

        gameInfoPanel.repaint();
    }

}
