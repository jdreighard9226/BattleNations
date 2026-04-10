package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public class CapitalDominationWorld extends AbstractWorld {
    private int territoryCapitals;

    public CapitalDominationWorld(ArrayList<Region> regions) {
        super(regions);
        setTerritoryCapitals();
    }

    public CapitalDominationWorld() {
        super();
        setTerritoryCapitals();
    }

    @Override
    public boolean isGameWon(List<Player> players) {
        int totalCapitals = getCapitalTerritoriesCount();
        for (Player player : players) {
            if (getCapitalTerritoriesCountOwnedByPlayer(player) == totalCapitals) {
                return true;
            }
        }
        return false;
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
            }

        }
        return temp;
    }

    private void setTerritoryCapitals() {
        this.territoryCapitals = getCapitalTerritoriesCount();
    }


    public Player getWinningPlayer(List<Player> players) {
        for (Player player : players) {
            if (getCapitalTerritoriesCountOwnedByPlayer(player) == territoryCapitals) {
                return player;
            }
        }
        return null;
    }
}
