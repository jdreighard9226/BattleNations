package game;

import map.Territory;
import map.World;
import player.Player;

public class ReinforcementService {
    private static final int TERRITORY_COUNT_DIVISOR = 3;
    private static final int MINIMUM_TROOPS_RETURNED = 3;

    public ReinforcementService() {
    }

    public int calculateReinforcements(Player player, World world) {
        int playerTerritoryCount = world.getTerritoryCountOwnedByPlayer(player);
        int baseTroops = playerTerritoryCount / TERRITORY_COUNT_DIVISOR;
        baseTroops = Math.max(baseTroops, MINIMUM_TROOPS_RETURNED);

        int terrainBonusTroops = 0;
        for (Territory territory : world.getTerritoriesOwnedByPlayer(player)) {
            terrainBonusTroops += territory.getTerrain().getTroopBonus();
        }
        return baseTroops + terrainBonusTroops;

    }

    public void reinforce(Player player, Territory territory, int troopsToAdd) {
        validateReinforce(player, territory, troopsToAdd);
        player.setTroopsToPlace(player.getTroopsToPlace() - troopsToAdd);
        territory.setTroopAmount(territory.getTroopAmount() + troopsToAdd);
    }

    private void validateReinforce(Player player, Territory territory, int troopsToAdd) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        } else if (territory == null) {
            throw new IllegalArgumentException("Territory cannot be null");
        } else if (territory.getPlayer() == null || !territory.getPlayer().equals(player)) {
            throw new IllegalArgumentException("territory must be owned by player");
        } else if (troopsToAdd <= 0) {
            throw new IllegalArgumentException("Troops to add must be at least 1");
        } else if (player.getTroopsToPlace() < troopsToAdd) {
            throw new IllegalArgumentException("Player doesn't have enough troops to place");
        }
    }
}
