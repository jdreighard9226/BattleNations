package map;

public class TestTerrainType {

    private static boolean testCreateTerrainType() {
        System.out.print("testing enumerators getTerrain() method: ");
        boolean passed = true;
        Terrain city = TerrainType.CITY.getTerrain();
        Terrain mountain = TerrainType.MOUNTAIN.getTerrain();
        Terrain desert = TerrainType.DESERT.getTerrain();
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
