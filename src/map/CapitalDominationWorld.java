package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public class CapitalDominationWorld extends AbstractWorld {
    private int territoryCapitals;
    public CapitalDominationWorld(ArrayList<Region> regions) {
        super(regions);
    }

    public CapitalDominationWorld() {
        super();
    }

    @Override
    public boolean isGameWon(List<Player> players) {

        for (Player player : players) {
            int playerTerritoryCount = getCapitalTerritoriesCountOwnedByPlayer(player);
            if (playerTerritoryCount != 6 && getCapitalTerritoriesCountOwnedByPlayer(player) != 0) {
                return false;
            }
        }
        return true;
    }

    private int getCapitalTerritoriesCountOwnedByPlayer(Player player) {
        int temp = 0;
        for (Region region : regions) {
            if (region.getCapitalTerritory().getPlayer().equals(player)) {
                temp++;
            }
        }
        return temp;
    }

    private int getCapitalTerritoriesCount() {
        int temp = 0;
        for (Region region : regions) {
            if (region.getCapitalTerritory() != null) {
                temp++;
            };

        }
        return temp;
    }

    private void setTerritoryCapitals() {
        this.territoryCapitals = getCapitalTerritoriesCount();
    }
    private int getTerritoryCapitals() {
        return territoryCapitals;
    }
}
