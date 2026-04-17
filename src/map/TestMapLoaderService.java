package map;

import terrain.*;

import java.awt.*;
import java.util.List;

public class TestMapLoaderService {

    public static void main(String[] args) {
        boolean passed = true;

        System.out.println("Testing MapLoaderService");

        if (!testLoadOnlyCaptialsTerrain()) passed = false;
        if (!testLoadOnlyCitiesTerrain()) passed = false;
        if (!testLoadOnlyDesertsTerrain()) passed = false;
        if (!testLoadOnlyMountainsTerrain()) passed = false;
        if (!testLoadOnlyPlainsTerrain()) passed = false;
        if (!testLoadMixedTerrain()) passed = false;

        if (!testLoadOnlyCapitalsRegionAssignment()) passed = false;
        if (!testLoadMixedRegionAssignment()) passed = false;

        if (passed) {
            System.out.println("All Tests Passed");
        } else {
            System.err.println("At least One Test Failed");
        }

    }

    private static boolean testLoadOnlyCaptialsTerrain() {
        System.out.print("Testing only capitals terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_capitals", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (territories[i][j] == null) {
                    passed = false;
                    System.err.println("Found null territories where territories should have been.");
                }
                else if (!(territories[i][j].getTerrain() instanceof CityTerrain)) {
                    passed = false;
                    System.err.println("Found non city territories in city only map.");
                } else if (!territories[i][j].isCapital()) {
                    passed = false;
                    System.err.println("Found non capitals in a capital only map.");
                }
                if (!passed) {
                    break;
                }
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }

    private static boolean testLoadOnlyCitiesTerrain() {
        System.out.print("Testing only cities terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_city", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (territories[i][j] == null) {
                    passed = false;
                    System.err.println("Found null territories where territories should have been.");
                }
                else if (!(territories[i][j].getTerrain() instanceof CityTerrain)) {
                    passed = false;
                    System.err.println("Found non city territories in city only map.");
                } else if (territories[i][j].isCapital()) {
                    passed = false;
                    System.err.println("Found capitals in a no capital map.");
                }
                if (!passed) {
                    break;
                }
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadOnlyDesertsTerrain() {
        System.out.print("Testing only desert terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_desert", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (territories[i][j] == null) {
                    passed = false;
                    System.err.println("Found null territories where territories should have been.");
                }
                else if (!(territories[i][j].getTerrain() instanceof DesertTerrain)) {
                    passed = false;
                    System.err.println("Found non desert territories in desert only map.");
                }
                if (!passed) {
                    break;
                }
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadOnlyMountainsTerrain() {
        System.out.print("Testing only mountain terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_mountains", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (territories[i][j] == null) {
                    passed = false;
                    System.err.println("Found null territories where territories should have been.");
                }
                else if (!(territories[i][j].getTerrain() instanceof MountainTerrain)) {
                    passed = false;
                    System.err.println("Found non mountain territories in mountain only map.");
                }
                if (!passed) {
                    break;
                }
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadOnlyPlainsTerrain() {
        System.out.print("Testing only plains terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_plains", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (territories[i][j] == null) {
                    passed = false;
                    System.err.println("Found null territories where territories should have been.");
                }
                else if (!(territories[i][j].getTerrain() instanceof PlainTerrain)) {
                    passed = false;
                    System.err.println("Found non plains territories in plain only map.");
                }
                if (!passed) {
                    break;
                }
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadMixedTerrain() {
        System.out.print("Testing mixed terrain map: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/mixed", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        if (territories[0][0] == null || !(territories[0][0].getTerrain() instanceof CityTerrain) || !territories[0][0].isCapital()) {
            passed = false;
            System.err.println("Expected Capital City at (0,0)");
        }
        if (territories[0][1] == null || !(territories[0][1].getTerrain() instanceof CityTerrain) || territories[0][1].isCapital()) {
            passed = false;
            System.err.println("Expected non capital City at (0,1)");
        }
        if (territories[0][2] == null || !(territories[0][2].getTerrain() instanceof DesertTerrain)) {
            passed = false;
            System.err.println("Expected Desert Terrain at (0,2)");
        }
        if (territories[0][3] == null || !(territories[0][3].getTerrain() instanceof MountainTerrain)) {
            passed = false;
            System.err.println("Expected Mountain Terrain at (0,3)");
        }
        if (territories[0][4] == null || !(territories[0][4].getTerrain() instanceof PlainTerrain)) {
            passed = false;
            System.err.println("Expected Plain Terrain at (0,4)");
        }
        if (territories[1][0] == null || !(territories[1][0].getTerrain() instanceof WaterTerrain)) {
            passed = false;
            System.err.println("Expected Water Terrain at (1,0)");
        }
        if (territories[1][1] == null || !(territories[1][1].getTerrain() instanceof WaterRouteTerrain)) {
            passed = false;
            System.err.println("Expected Water Route Terrain at (1,1)");
        }
        if (territories[1][2] == null || !(territories[1][2].getTerrain() instanceof CityTerrain) || !territories[1][2].isCapital()) {
            passed = false;
            System.err.println("Expected Capital City at (1,2)");
        }
        if (territories[1][3] == null || !(territories[1][3].getTerrain() instanceof CityTerrain) || territories[1][3].isCapital()) {
            passed = false;
            System.err.println("Expected non capital City at (1,3)");
        }
        if (territories[2][0] == null || !(territories[2][0].getTerrain() instanceof DesertTerrain)) {
            passed = false;
            System.err.println("Expected Desert Terrain at (2,0)");
        }
        if (territories[2][1] == null || !(territories[2][1].getTerrain() instanceof MountainTerrain)) {
            passed = false;
            System.err.println("Expected Mountain Terrain at (2,1)");
        }
        if (territories[2][2] == null || !(territories[2][2].getTerrain() instanceof PlainTerrain)) {
            passed = false;
            System.err.println("Expected Plain Terrain at (2,2)");
        }
        if (territories[2][3] == null || !(territories[2][3].getTerrain() instanceof WaterTerrain)) {
            passed = false;
            System.err.println("Expected Water Terrain at (2,3)");
        }
        if (territories[2][4] == null || !(territories[2][4].getTerrain() instanceof WaterRouteTerrain)) {
            passed = false;
            System.err.println("Expected Water Route Terrain at (2,4)");
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadOnlyCapitalsRegionAssignment() {
        System.out.print("Testing region assignment for capitals: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/all_capitals", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        List<Region> regions =  data.regions();
        int regionCheck = 0;
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                switch (regionCheck) {
                    case 0:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 1 should have contained territory (0,0)");
                            passed = false;
                        }
                        break;
                    case 1:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 2 should have contained territory (0,1)");
                            passed = false;
                        }
                        break;
                    case 2:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 3 should have contained territory (0,2)");
                            passed = false;
                        }
                        break;
                    case 3:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 4 should have contained territory (1,0)");
                            passed = false;
                        }
                        break;
                    case 4:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 5 should have contained territory (1,1)");
                            passed = false;
                        }
                        break;
                    case 5:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 6 should have contained territory (2,0)");
                            passed = false;
                        }
                        break;
                    case 6:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 7 should have contained territory (2,1)");
                            passed = false;
                        }
                        break;
                    case 7:
                        if (!regions.get(regionCheck).hasTerritory(territories[i][j])) {
                            System.err.println("Region 8 should have contained territory (2,2)");
                            passed = false;
                        }
                        break;
                    }
                regionCheck++;
                }
            }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
    private static boolean testLoadMixedRegionAssignment() {
        System.out.print("Testing region assignment for capitals: ");
        boolean passed = true;
        MapLoaderService service = new MapLoaderService();
        MapLoaderData data = service.loadTerritories("src/mapLoaderServiceTestTextFiles/mixed", new Dimension(1000, 800));
        Territory[][] territories = data.territories();
        List<Region> regions =  data.regions();
        int regionCheck = 0;
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            for (int j = 0; j < territories[i].length - alternator; j++) {
                if (regionCheck < 5) {
                    if(!regions.get(0).hasTerritory(territories[i][j])) {
                        System.err.println("Region 1 should have contained territory (" + i + "," + j + ")");
                        passed = false;
                    }
                } if (regionCheck > 7 && regionCheck < 12){
                    if(!regions.get(1).hasTerritory(territories[i][j])) {
                        System.err.println("Region 2 should have contained territory (" + i + "," + j + ")");
                        passed = false;
                    }
                }
                regionCheck++;
            }
        }
        if (passed) System.out.println("Passed");
        else System.err.println("Failed");
        return passed;
    }
}
