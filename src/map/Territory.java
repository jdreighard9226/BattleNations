package map;

import player.Player;

import java.awt.*;

public class Territory extends Polygon implements Comparable<Territory> {
    private Player player;
    private final Terrain terrain;
    private int troopAmount;
    private boolean isPlayerOwned;


    public Territory(Player player, Terrain terrain, int troopAmount, int[] xCoords, int[] yCoords) {
        this.player = player;
        this.terrain = terrain;
        this.troopAmount = troopAmount;
        super.xpoints = xCoords;
        super.ypoints = yCoords;
        this.isPlayerOwned = true;
    }
    public Territory(Terrain terrain, int[] xCoords, int[] yCoords) {
        this.terrain = terrain;
        super.xpoints = xCoords;
        super.ypoints = yCoords;
        this.isPlayerOwned = false;
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

    public Terrain getTerrain() {
        return terrain;
    }

    public boolean getIsPlayerOwned() {
        return isPlayerOwned;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTroopAmount(int troopAmount) {
        this.troopAmount = troopAmount;
    }

    public void setIsPlayerOwned(boolean isPlayerOwned) {
        this.isPlayerOwned = isPlayerOwned;
    }

    public void Draw(Graphics g) {
        g.setColor(this.getCurrentColor());
        g.drawPolygon(super.xpoints, super.ypoints, super.xpoints.length);
        g.fillPolygon(super.xpoints, super.ypoints, super.xpoints.length);
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
        return("Territory ID: " + "add id" + " Player: " + player.getName() + " Troop Count: " + troopAmount);
    }
}
