package map;

import player.Player;

import java.awt.*;

public class Territory extends Polygon implements Comparable<Territory> {
    private Player player;
    private final Terrain terrainType;
    private int troopAmount;
    private int identifier = 1;


    public Territory(Player player, TerrainType terrainType, int troopAmount, int[] xCoords, int[] yCoords) {
        this.player = player;
        this.terrainType = TerrainType.create(terrainType);
        this.troopAmount = troopAmount;
        this.identifier = getIdentifier();
        incrementIdentifier();
        super.xpoints = xCoords;
        super.ypoints = yCoords;
    }
    public Territory(TerrainType terrainType, int[] xCoords, int[] yCoords) {
        this.terrainType = TerrainType.create(terrainType);
        super.xpoints = xCoords;
        super.ypoints = yCoords;
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

    public int getIdentifier() {
        return identifier;
    }

    public Terrain getTerrainType() {
        return terrainType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTroopAmount(int troopAmount) {
        this.troopAmount = troopAmount;
    }

    public void incrementIdentifier() {
        identifier++;
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

    @Override
    public String toString() {
        return("Territory ID: " + identifier + " Player: " + player.getName() + " Troop Count: " + troopAmount);
    }
}
