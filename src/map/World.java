package map;

import player.Player;

import java.util.List;

public interface World {
    List<Region> getRegions();

    List<Territory> getAllTerritories();

    void addRegion(Region region);

    void removeRegion(Region region);

    void clearWorld();

    int getTerritoryCount();

    List<Territory> getTerritoriesOwnedByPlayer(Player player);

    int getTerritoryCountOwnedByPlayer(Player player);

    List<Region> getRegionsControlledByPlayer(Player player);

    int getRegionCountControlledByPlayer(Player player);

    boolean isRegionControlledByPlayer(Region region, Player player);

    boolean isGameWon(List<Player> players);
}
