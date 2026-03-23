package startGUI;

import player.Player;

import java.util.List;

public class SetUpData {
    private String map;
    private List<Player> players;
    private boolean randomTerritories;
    private boolean randomTroopPlacement;
    public SetUpData() {
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public boolean isRandomTroopPlacement() {
        return randomTroopPlacement;
    }

    public void setRandomTroopPlacement(boolean randomTroopPlacement) {
        this.randomTroopPlacement = randomTroopPlacement;
    }

    public boolean isRandomTerritories() {
        return randomTerritories;
    }

    public void setRandomTerritories(boolean randomTerritories) {
        this.randomTerritories = randomTerritories;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
