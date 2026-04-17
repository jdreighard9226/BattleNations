package setUpGUI;

import gameGUI.GameController;
import map.Territory;
import player.Player;
import terrain.WaterRouteTerrain;
import terrain.WaterTerrain;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class TerritorySelectionPhase {

    private SetUpController setUpController;

    private Territory[][] territories;

    private Player activePlayer;

    private final List<Player> players;

    private int playerTurn = 0;

    private boolean isRandom;


    public TerritorySelectionPhase(SetUpController setUpController, Territory[][] territories, List<Player> players, Boolean isRandom) {
        this.setUpController = setUpController;
        this.territories = territories;
        this.players = players;
        this.isRandom = isRandom;
        if(isRandom){
            randomlyAssigningTerritories();
        }
    }

    public void dealWithClick(Point point) {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory != null && territory.contains(point)) {
                    assigningTerritory(territory);
                }
            }
        }
    }

    public void assigningTerritory(Territory territory) {
        if (territory.getPlayer() == null) {
            if (territory.getTerrain() instanceof WaterTerrain || territory.getTerrain() instanceof WaterRouteTerrain) {
                setUpController.getErrorText().setText("Cannot select a water or water route Territory. Select A different one ");
                setUpController.getGameInfoPanel().repaint();
            } else {
                territory.setPlayer(activePlayer);
                territory.setTroopAmount(1);
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
                setUpController.updateGameInfoText();
                if (!unownedTerritory()) {
                    setUpController.placeTroopPhase();
                }
            }
        } else {
            setUpController.getErrorText().setText("Territory is already Owned, Select A different one");
            setUpController.getGameInfoPanel().repaint();
        }

    }

    public boolean unownedTerritory() {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory != null && territory.getPlayer() == null && !(territory.getTerrain() instanceof WaterTerrain) && !(territory.getTerrain() instanceof WaterRouteTerrain)) {
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
            if (territories[row][col] != null && territories[row][col].getPlayer() == null && !(territories[row][col].getTerrain() instanceof WaterTerrain) && !(territories[row][col].getTerrain() instanceof WaterRouteTerrain)) {
                territories[row][col].setPlayer(activePlayer);
                territories[row][col].setTroopAmount(1);
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
                setUpController.updateGameInfoText();
            }
        }
        setUpController.placeTroopPhase();
    }
}
