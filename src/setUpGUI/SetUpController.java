package setUpGUI;

import map.MapDisplay;
import map.Territory;
import player.Player;
import startGUI.*;
import terrain.WaterTerrain;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SetUpController {
    private final JFrame display;

    private final SetUpData gameSetUpData;

    private final MapDisplay mapDisplay;

    private final Territory[][] territories;

    private String faze;

    private Player activePlayer;

    private final List<Player> players;

    private int playerTurn = 0;

    public SetUpController(JFrame display, SetUpData setUpData) {
        this.display = display;
        this.gameSetUpData = setUpData;
        faze = "Territory";

        mapDisplay = new MapDisplay(gameSetUpData, this);
        territories = mapDisplay.getTerritories();

        players = gameSetUpData.getPlayers();
        activePlayer = players.getFirst();

        displayMapDisplay();

        if (gameSetUpData.isRandomTerritories()){
            randomlyAssigningTerritories();
        }

    }

    public JFrame getDisplay() {
        return display;
    }

    public SetUpData getGameSetUpData() {
        return gameSetUpData;
    }

    public void displayMapDisplay() {
        mapDisplay.addMapDisplay(this.display);
    }

    public void getTerritoryClicked(Point point) {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory != null && territory.contains(point)) {
                    if (faze.equals("Territory")) {
                        assigningTerritory(territory);
                    }
                    if (faze.equals("Troop")){
                        placeTroop(territory);
                    }
                }
            }
        }
    }

    public void assigningTerritory(Territory territory) {
        if (territory.getPlayer() == null) {
            if (territory.getTerrain() instanceof WaterTerrain) {
                JOptionPane.showMessageDialog(display, "Cannot select water Territory");
            } else {
                territory.setPlayer(activePlayer);
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
                if (!unownedTerritory()) {
                    placeTroopFaze();
                }
            }
        } else {
            JOptionPane.showMessageDialog(display, "Territory already owned");
        }

    }

    public boolean unownedTerritory() {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory.getPlayer() == null && !(territory instanceof WaterTerrain)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomlyAssigningTerritories() {
        Random random = new Random();
        while (unownedTerritory()) {
            int row = random.nextInt(territories.length);
            int col = random.nextInt(territories[row].length);
            if (territories[row][col] != null && territories[row][col].getPlayer() == null) {
                territories[row][col].setPlayer(activePlayer);
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
            }
        }
        placeTroopFaze();
    }

    public void placeTroopFaze() {
        if (gameSetUpData.isRandomTroopPlacement()) {
            randomlyPlaceTroops();
        } else {
            faze = "Troop";
        }
    }

    public void randomlyPlaceTroops() {

    }

    public void placeTroop(Territory territory) {

    }
}
