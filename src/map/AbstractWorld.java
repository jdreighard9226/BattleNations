package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements World {
    protected final List<Region> regions;

    public AbstractWorld(List<Region> regions) {
        this.regions = new ArrayList<>(regions);
    }

    public AbstractWorld() {
        this.regions = new ArrayList<>();
    }

    public List<Region> getRegions() {
        return this.regions;
    }

    public List<Territory> getAllTerritories() {
        List<Territory> territories = new ArrayList<>();
        for (Region region : regions) {
            territories.addAll(region.getTerritories());
        }
        return territories;
    }

    public int getTerritoryCount() {
        return getAllTerritories().size();
    }

    public List<Territory> getTerritoriesOwnedByPlayer(Player player) {
        List<Territory> allTerritories = getAllTerritories();
        List<Territory> playerTerritories = new ArrayList<>();

        for (Territory territory : allTerritories) {
            if (player.equals(territory.getPlayer())) {
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
        for (Region region : regions) {
            if (region.isRegionConquered()) {
                if (isRegionControlledByPlayer(region, player)) {
                    playerOwnedRegions.add(region);
                }
            }
        }
        return playerOwnedRegions;
    }

    public int getRegionCountControlledByPlayer(Player player) {
        return getRegionsControlledByPlayer(player).size();

    }

    public boolean isRegionControlledByPlayer(Region region, Player player) {
        Player controllingPlayer = region.getPlayerConqueredRegion();
        return (controllingPlayer != null && controllingPlayer.equals(player));

    }

    public abstract boolean isGameWon(List<Player> players);
}
