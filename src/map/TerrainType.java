/**
 *
 * Outside Sources:
 * Enum Source: https://www.w3schools.com/java/java_enums.asp
 * I knew an Enum would make creating TerrainTypes much simplier, and easy to scale.
 * I referenced w3 schools Java Enums Page to see code examples of how to set it up.
 * My Terrian Enum was inspired by their "Enum in a Switch Statement" section
 */
package map;

public abstract class TerrainType {
    protected final double attackBonus;
    protected final double defenseBonus;
    protected final int troopBonus;
    protected final String imageFile;

    public TerrainType(double attackBonus, double defenseBonus, String imageFile, int troopBonus) {
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

    public enum Terrain {
        CITY, DESERT, MOUNTAIN
    }

    public static TerrainType create(Terrain terrain) {
        switch (terrain) {
            case CITY:
                return new CityTerrain();
            case DESERT:
                return new DesertTerrain();
            case MOUNTAIN:
                return new MountainTerrain();

        }
        ;
        throw new IllegalArgumentException("Someone messed up");
    }

    private static boolean testCreateTerrainType() {
        System.out.print("testing create() method: ");
        boolean passed = true;
        TerrainType city = TerrainType.create(Terrain.CITY);
        TerrainType mountain = TerrainType.create(Terrain.MOUNTAIN);
        TerrainType desert = TerrainType.create(Terrain.DESERT);
        if (!city.getClass().equals(CityTerrain.class)) {
            passed = false;
            System.err.println("Error In City TerrainType");
        }

        if (!mountain.getClass().equals(MountainTerrain.class)) {
            passed = false;
            System.err.println("Error in Mountain TerrainType");

        }

        if (!desert.getClass().equals(DesertTerrain.class)) {
            passed = false;
            System.err.println("Error in Desert TerrainType");
        }
        if (passed) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
        return passed;
    }

    public static void main(String[] args) {
        boolean passed = true;
        System.out.println("Testing Methods");
        if (!testCreateTerrainType()) passed = false;
        if (passed) {
            System.out.println("All Tests Passed");
        } else {
            System.err.println("At least one Test Failed");
        }
    }
}
