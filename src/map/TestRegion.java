/**
 *
 * Sources:
 * Google AI Overview:
 * I Looked up how to add multiple elements to a list at once.
 * Found the Collections.addAll(list, "item1", "item2", "item3");
 * from the overview response. I use that in my test code to make adding territories to a temporary list easier.
 *
 * NOTE: Test code is not fully working yet
 */
package map;

import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.awt.Color.BLUE;

public class TestRegion {

    public static void main(String[] args) {
        boolean passed = true;
        System.out.println("Testing Methods");
        if (!testSingleTerritoryInit()) passed = false;
        if (passed) {
            System.out.println("All Tests Passed");
        } else {
            System.err.println("At least one Test Failed");
        }
    }

    public static boolean testSingleTerritoryInit() {
        System.out.print("testing Initializer when Adding 1 Territory: ");
        boolean passed = true;
        Player player = new Player("t1", BLUE);
        Territory t1 = new Territory(player, TerrainType.DESERT, 20);
        Region region = new Region(t1);

        if (region.getSize() != 1) {
            System.err.println("Fail: The size of region was not 1");
            passed = false;
        }

        if (!region.hasTerritory(t1)) {
            System.err.println("Fail: The region does not contain the proper territory");
            passed = false;
        }
        if (passed) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
        return passed;
    }

    public static boolean testMultipleTerritoriesInit() {
        boolean passed = true;
        // setting up all required elements for a region
        Player player = new Player("t1", BLUE);
        Territory t1 = new Territory(player, TerrainType.DESERT, 20);
        Territory t2 = new Territory(player, TerrainType.MOUNTAIN, 10);
        Territory t3 = new Territory(player, TerrainType.DESERT, 30);
        Territory t4 = new Territory(player, TerrainType.MOUNTAIN, 50);
        Territory t5 = new Territory(player, TerrainType.CITY, 20);
        List<Territory> temp = new ArrayList<>();
        Collections.addAll(temp, t1, t2, t3, t4, t5);
        int regionSize = temp.size();

        // add all territories to a region
        Region region = new Region(temp);
        if (region.getSize() != regionSize) {
            passed = false;
            System.err.println("Region was not the proper size");
        }

        if (!temp.equals(region.getTerritories())) {
            passed = false;
            System.err.println("regions territories are not the same as what was passed into initializer");
        }

        for (Territory t : temp) {
            if (!region.hasTerritory(t)) {
                System.err.println("territory");
            }
        }
        return passed;
    }

    // TODO: finish test code for Add Territory
    public boolean testAddTerritory() {
        Player player = new Player("t1", BLUE);
        Territory t1 = new Territory(player, TerrainType.DESERT, 20,
                new int[]{1,2,3,4,5,6},
                new int[]{1,2,3,4,5,6});

        Region region = new Region(t1);

        return false;
    }
}
