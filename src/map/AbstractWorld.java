package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements World {
    protected final List<Region> regions;

    public AbstractWorld(List<Region> regions) {
        this.regions = regions;
    }

    public AbstractWorld() {
        this.regions = new ArrayList<>();
    }

   public List<Region> getRegions() {
        return this.regions;
   }

    public List<Territory> getAllTerritories() {
        List<Territory> temp;
        List<Territory> territories = new ArrayList<>();
        for (Region region : regions) {
            temp = region.getTerritories();
            territories.addAll(temp);
        }
        return territories;
    }

   public void addRegion(Region region) {
        regions.add(region);
   }

    public void removeRegion(Region region) {
        regions.remove(region);
    }

    public void clearWorld() {
        regions.clear();
    }

    public int getTerritoryCount() {
        return getAllTerritories().size();
    }

    public List<Territory> getTerritoriesOwnedByPlayer(Player player) {
            List<Territory> allTerritories = getAllTerritories();
            List<Territory> playerTerritories = new ArrayList<>();
            for (Territory territory: allTerritories) {
                if (territory.getPlayer().equals(player)) {
                    playerTerritories.add(territory);
                }
            }
            return playerTerritories;
    }

    public int getTerritoryCountOwnedByPlayer(Player player) {
        return getTerritoriesOwnedByPlayer(player).size();
    }

    public List<Region> getRegionsControlledByPlayer(Player player) {
        List<Region> playerOwnedRegions = new ArrayList<>();
        for(Region region : regions) {
            if (region.isRegionConquered()) {
                if (isRegionControlledByPlayer(region, player)) {
                    playerOwnedRegions.add(region);
                };
            };
        }
        return playerOwnedRegions;
    }

    public int getRegionCountControlledByPlayer(Player player) {
        return getRegionsControlledByPlayer(player).size();

    }

    public boolean isRegionControlledByPlayer(Region region, Player player) {
        if (region.getPlayerConqueredRegion().equals(player)) {
            return true;
        }
        return false;
    }

    public abstract boolean isGameWon(List<Player> players);
}
