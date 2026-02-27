package map;

import player.Player;

import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

import static java.awt.Color.*;

public class Region {
    private List<Territory> territories = new ArrayList<>();

    // cant have a region without at least 1 territory
    public Region(Territory territory) {
        territories.add(territory);
    }

    // constructor for multiple territories
    public Region(List<Territory> territories) {
        this.territories = territories;
    }

    public boolean isRegionConquered() {
        Color firstColor = territories.get(0).getCurrentColor();
        for (int i = 1; i < territories.toArray().length; i++) {
            if (!firstColor.equals(territories.get(i).getCurrentColor())) {
                return false;
            }
        }
        return true;
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }

    public void addInitialTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    public void removeTerritory(Territory territory) {
        territories.remove(territory);
    }
    // TODO: write test code for both single territory init, and multiple territories init

    // TODO: finish test code for Add Territory
    public boolean testAddTerritory() {
        Player player = new Player("t1", BLUE);
        Territory t1 = new Territory(player, TerrainType.Terrain.DESERT, 20);
        Region region = new Region(t1);

        return false;
    }

}
