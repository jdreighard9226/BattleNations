package map;

import player.Player;

import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

public class Region {
    private List<Territory> territories = new ArrayList<>(1);

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

    public int getSize() {
        return territories.size();
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public boolean hasTerritory(Territory territory) {
        return territories.contains(territory);
    }
}
