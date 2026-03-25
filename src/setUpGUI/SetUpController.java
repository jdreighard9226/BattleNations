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

    private int troopsToPlace = 0;

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
                if (territory != null && territory.getPlayer() == null && !(territory.getTerrain() instanceof WaterTerrain)) {
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
            if (territories[row][col] != null && territories[row][col].getPlayer() == null && !(territories[row][col].getTerrain() instanceof WaterTerrain)) {
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
        giveTroopsToPlace(120);
        if (gameSetUpData.isRandomTroopPlacement()) {
            randomlyPlaceTroops();
        } else {
            faze = "Troop";
        }
    }

    public void giveTroopsToPlace(int troopsToPlace) {
        int troopsPerPlayer = troopsToPlace/players.size();
        for (Player player: players){
            player.setTroopsToPlace(troopsPerPlayer);
        }
    }

    public void randomlyPlaceTroops() {

    }

    public void placeTroop(Territory territory) {
        if (territory.getPlayer() != activePlayer) {
            if (!(territory.getTerrain() instanceof WaterTerrain)) {
                territory.setTroopAmount(territory.getTroopAmount()+1);
                activePlayer.setTroopsToPlace(activePlayer.getTroopsToPlace() - 1);
                troopsToPlace--;
                if (troopsToPlace <= 0) {
                    playerTurn++;
                    if (activePlayer.getTroopsToPlace() > 3) {
                        troopsToPlace = 3;
                    } else {
                        troopsToPlace = activePlayer.getTroopsToPlace();
                    }
                }
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);

            }
        } else {
            JOptionPane.showMessageDialog(display, "Territory not owned by" + activePlayer.getName());
        }
    }

    public boolean playersHaveTroopsToPlace() {
        for (Player player: players) {
            if (player.getTroopsToPlace() > 0) {
                return true;
            }
        }
        return false;
    }
}
