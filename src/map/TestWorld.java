package map;

import player.Player;
import terrain.TerrainType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.BLUE;

public class TestWorld {

    public static void main(String[] args) {
        boolean passed = true;
        System.out.println("Testing Methods");
        if (!testGetTerritoryCount()) passed = false;
        if (!testGetAllTerritories()) passed = false;
        if (passed) {
            System.out.println("All Tests Passed");
        } else {
            System.err.println("At least one Test Failed");
        }
    }

    private static class WorldSetupWrapper {
        private DummyWorld world;
        private Player player1;

        WorldSetupWrapper(DummyWorld world, Player player1) {
            this.world = world;
            this.player1 = player1;
        }
    }

    private static WorldSetupWrapper generateWorld() {
        Player player1 = new Player("p1", BLUE);
        ArrayList<Region> regions = new ArrayList<>();
        // add all territories to a region
        for (int i = 0; i < 5; i++) {
            ArrayList<Territory> territories = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Territory t1 = new Territory(player1, TerrainType.DESERT.getTerrain(), 20,
                        new int[]{1, 2, 3, 4, 5, 6},
                        new int[]{1, 2, 3, 4, 5, 6}, false);
                territories.add(t1);
            }
            Region region = new Region(territories);
            regions.add(region);
        }
        return new WorldSetupWrapper(new DummyWorld(regions), player1);
    }

    private static boolean testGetAllTerritories() {
        boolean passed = true;
        System.out.print("Testing getAllTerritories method: ");
        WorldSetupWrapper setup = generateWorld();
        DummyWorld world = setup.world;

        ArrayList<Territory> allTerritories = new ArrayList<>();
        for (Region region : world.getRegions()) {
            for (Territory territory : region.getTerritories()) {
                allTerritories.add(territory);
            }
        }

        List<Territory> worldTerritories = world.getAllTerritories();

        for (Territory territory : allTerritories) {
            if (!worldTerritories.contains(territory)) {
                passed = false;
                System.err.println("get all territories, did not contain a territory from initial world creation ");
            }
        }

        Player temp = new Player("p2", Color.RED);
        // test getAll after adding a territory to each region
        for (Region region : world.getRegions()) {
            Territory tempTerritory = new Territory(temp, TerrainType.DESERT.getTerrain(), 20,
                    new int[]{1, 2, 3, 4, 5, 6},
                    new int[]{1, 2, 3, 4, 5, 6}, false);
            region.addTerritory(tempTerritory);
            allTerritories.add(tempTerritory);

        }

        worldTerritories = world.getAllTerritories();
        for (Territory territory : allTerritories) {
            if (!worldTerritories.contains(territory)) {
                passed = false;
                System.err.println("get all territories, did not contain a territory after adding one to each region");
            }
        }

        if (worldTerritories.size() != allTerritories.size()) {
            passed = false;
            System.err.println("getAllTerritories returned the wrong number of territories");
        }

        for (Region region : world.getRegions()) {
            for (int i = 0; i < 100; i++) {
                Territory tempTerritory = new Territory(temp, TerrainType.DESERT.getTerrain(), 20,
                        new int[]{1, 2, 3, 4, 5, 6},
                        new int[]{1, 2, 3, 4, 5, 6}, false);
                region.addTerritory(tempTerritory);
                allTerritories.add(tempTerritory);
            }

            worldTerritories = world.getAllTerritories();
            for (Territory territory : allTerritories) {
                if (!worldTerritories.contains(territory)) {
                    passed = false;
                    System.err.println("get all territories, did not contain a territory after adding 100 territories to one region");
                }
            }

            if (worldTerritories.size() != allTerritories.size()) {
                passed = false;
                System.err.println("getAllTerritories returned the wrong number of territories");
            }
        }

        worldTerritories = world.getAllTerritories();
        for (Territory territory : allTerritories) {
            if (!worldTerritories.contains(territory)) {
                passed = false;
                System.err.println("get all territories, did not contain a territory after adding 100 territories to every region");
            }
        }

        if (worldTerritories.size() != allTerritories.size()) {
            passed = false;
            System.err.println("getAllTerritories returned the wrong number of territories");
        }

        if (passed) {
            System.out.println("Passed");
        } else {
            System.err.println("Failed");
        }

        return passed;
    }

    private static boolean testGetTerritoryCount() {
        boolean passed = true;
        System.out.print("Testing getTerritoryCount method: ");
        WorldSetupWrapper setup = generateWorld();
        DummyWorld world = setup.world;
        int territoryCount = 0;
        for (Region r : world.getRegions()) {
            territoryCount += r.getSize();
        }
        if (territoryCount != world.getTerritoryCount()) {
            passed = false;
            System.err.println("world did not produce the proper territory count after initial world creation");
        }

        Player temp = new Player("p2", Color.RED);
        for (Region r : world.getRegions()) {
            r.addTerritory(new Territory(temp, TerrainType.DESERT.getTerrain(), 20,
                    new int[]{1, 2, 3, 4, 5, 6},
                    new int[]{1, 2, 3, 4, 5, 6}, false));
            territoryCount++;
        }

        if (territoryCount != world.getTerritoryCount()) {
            passed = false;
            System.err.println("World did not get proper territory count after adding one territory to each region");
        }

        for (Region r : world.getRegions()) {
            for (int i = 0; i < 100; i++) {
                r.addTerritory(new Territory(temp, TerrainType.DESERT.getTerrain(), 20,
                        new int[]{1, 2, 3, 4, 5, 6},
                        new int[]{1, 2, 3, 4, 5, 6}, false));
                territoryCount++;
            }
            if (world.getTerritoryCount() != territoryCount) {
                passed = false;
                System.err.println("World did not get proper territory count after adding 100 territories to one region");
            }
        }
        if (world.getTerritoryCount() != territoryCount) {
            passed = false;
            System.err.println("World did not get proper territory count after all regions gained 100 territories");
        }

        if (passed) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        return passed;
    }

    private static boolean testGetTerritoriesOwnedByPlayer() {
        boolean passed = true;
        System.out.print("Testing getTerritoriesOwnedByPlayer method(): ");
        WorldSetupWrapper setup = generateWorld();
        DummyWorld world = setup.world;
        Player player1 = setup.player1;

        return false;
    }

    private static boolean testGetTerritoryCountOwnedByPlayer() {
        return false;
    }

    private static boolean testGetRegionCountControlledByPlayer() {
        return false;
    }

    private static boolean testIsRegionControlledByPlayer() {
        return false;
    }

    private static boolean testIsGameOverTotalDomination() {
        return false;
    }

    private static boolean testIsGameOverCapitalDomination() {
        return false;
    }
}
