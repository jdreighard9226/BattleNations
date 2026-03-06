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
    protected final double attackBonusPercentage;
    protected final double defenseBonusPercentage;
    protected final int troopBonus;
    protected final String imageFile;

    public Terrain(double attackBonusPercentage, double defenseBonusPercentage, String imageFile, int troopBonus) {
        this.attackBonusPercentage = attackBonusPercentage;
        this.defenseBonusPercentage = defenseBonusPercentage;
        this.troopBonus = troopBonus;
        this.imageFile = imageFile;
    }

    public double getAttackBonus() {
        return attackBonusPercentage;
    }

    public double getDefenseBonus() {
        return defenseBonusPercentage;
    }

    public int getTroopBonus() {
        return troopBonus;
    }

    public String getImageFile() {
        return imageFile;
    }
}
