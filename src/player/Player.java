/**
 * Description: This file represents a Player for Battle Nations.
 * The Player has a name, a color, and a number of troops they are allowed to place.
 * The number of troops to place will be calculated and passed to player each round.
 *
 * Filename: Player.Java
 * Author: J.D. Reighard
 * Date: 2/26/2026
 */
package player;
import java.awt.Color;

public class Player {
    // class level variables
    private String name;
    private Color color;
    private int troopsToPlace;
    private double attackBonusMultiplier;
    private double defenseBonusMultiplier;
    private int troopBonus;

    /**
     * Constructor for player
     * @param name the name this player will go by
     * @param color the color this player's territories will be.
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public double getAttackBonusMultiplier() {
        return attackBonusMultiplier;
    }

    public double getDefenseBonusMultiplier() {
        return defenseBonusMultiplier;
    }

    public int getTroopBonus() {
        return troopBonus;
    }
    /**
     * get amount of troops the player can place
     * @return troupsToPlace
     */
    public int getTroopsToPlace() {
        return troopsToPlace;
    }

    /**
     * get the name of player
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the color of player
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the amount of troops a player can place
     * @param troopsToPlace number of troops
     */
    public void setTroopsToPlace(int troopsToPlace) {
        this.troopsToPlace = troopsToPlace;
    }
    public void setAttackBonusMultiplier(double attackBonusMultiplier) {
        this.attackBonusMultiplier = attackBonusMultiplier;
    }

    public void setDefenseBonusMultiplier(double defenseBonusMultiplier) {
        this.defenseBonusMultiplier = defenseBonusMultiplier;
    }

    public void setTroopBonus(int troopBonus) {
        this.troopBonus = troopBonus;
    }
}

