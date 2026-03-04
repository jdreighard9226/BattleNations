/**
 *
 * Outside Sources:
 * Enum Source: https://www.w3schools.com/java/java_enums.asp
 * I knew an Enum would make creating TerrainTypes much simplier, and easy to scale.
 * I referenced w3 schools Java Enums Page to see code examples of how to set it up.
 * My Terrian Enum was inspired by their "Enum in a Switch Statement" section
 */
package map;

public abstract class Terrain {
    protected final double attackBonus;
    protected final double defenseBonus;
    protected final int troopBonus;
    protected final String imageFile;

    public Terrain(double attackBonus, double defenseBonus, String imageFile, int troopBonus) {
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.troopBonus = troopBonus;
        this.imageFile = imageFile;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public double getDefenseBonus() {
        return defenseBonus;
    }

    public int getTroopBonus() {
        return troopBonus;
    }

    public String getImageFile() {
        return imageFile;
    }
}
