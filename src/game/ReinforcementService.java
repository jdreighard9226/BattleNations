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

    public ValidationResult reinforce(Player player, Territory territory, int troopsToAdd) {
        ValidationResult result = validateReinforce(player, territory, troopsToAdd);
        if (!result.isValid()) {
            return result;
        }
        player.setTroopsToPlace(player.getTroopsToPlace() - troopsToAdd);
        territory.setTroopAmount(territory.getTroopAmount() + troopsToAdd);
        return new ValidationResult(true, "Territory has been reinforced");
    }

    private ValidationResult validateReinforce(Player player, Territory territory, int troopsToAdd) {
        if (player == null) {
            return new ValidationResult(false, "Player cannot be null");
        } else if (territory == null) {
            return new ValidationResult(false, "Territory cannot be null");
        } else if (territory.getPlayer() == null || !territory.getPlayer().equals(player)) {
            return new ValidationResult(false, "territory must be owned by player");
        } else if (troopsToAdd <= 0) {
            return new ValidationResult(false, "Troops to add must be at least 1");
        } else if (player.getTroopsToPlace() < troopsToAdd) {
            return new ValidationResult(false, "Player doesn't have enough troops to place");
        }

        return new ValidationResult(true, "Valid Reinforcement Move");
    }
}
