package map;

import player.Player;

import java.awt.Color;

public class Territory implements Comparable<Territory> {
    private Player player;
    private final TerrainType terrainType;
    private int troopAmount;

    public Territory(Player player, TerrainType.Terrain terrain, int troopAmount) {
        this.player = player;
        this.terrainType = TerrainType.create(terrain);
        this.troopAmount = troopAmount;
    }

    public int getTroopAmount() {
        return troopAmount;
    }

    public Color getCurrentColor() {
        return player.getColor();
    }

    public Player getPlayer() {
        return player;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTroopAmount(int troopAmount) {
        this.troopAmount = troopAmount;
    }

    @Override
    public int compareTo(Territory other) {
        if (this.troopAmount > other.troopAmount) {
            return 1;
        } else if (this.troopAmount == other.troopAmount) {
            return 0;
        } else {
            return -1;
        }
    }
}
